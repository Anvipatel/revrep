package com.org.revrep.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.org.revrep.backend.domain.converter.ReviewToReviewDAO;
import com.org.revrep.backend.domain.converter.ReviewDAOToReview;
import com.org.revrep.backend.domain.dao.ReviewDAO;
import com.org.revrep.backend.repository.ReviewRepo;
import com.org.revrep.interfaces.service.IReviewService;
import com.org.revrep.web.domain.Review;
import com.org.revrep.web.exception.BusinessValidationException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ReviewServiceImpl implements IReviewService {

	@Autowired
	private ReviewRepo reviewRepo;

	private ReviewToReviewDAO reviewFormToReviewInfo;
	private ReviewDAOToReview reviewInfoToReviewForm;

	@Autowired
	public ReviewServiceImpl(ReviewRepo reviewRepo, ReviewToReviewDAO reviewFormToReviewInfo,
			ReviewDAOToReview reviewInfoToReviewForm) {
		this.reviewRepo = reviewRepo;
		this.reviewFormToReviewInfo = reviewFormToReviewInfo;
		this.reviewInfoToReviewForm = reviewInfoToReviewForm;
	}

	@Override
	@Transactional
	public Review addNewReview(Review reviewForm) {
		if (Objects.isNull(reviewForm)) {
			throw new BusinessValidationException("Error");
		}
		ReviewDAO reviewDao = reviewRepo.save(reviewFormToReviewInfo.convert(reviewForm));

		log.info("Saved review Id: " + reviewDao.get_id());
		return reviewInfoToReviewForm.convert(reviewDao);
	}

	@Override
	public List<Review> getReviews(String prodId) {
		List<ReviewDAO> reviewDaos = reviewRepo.findByProductId(prodId);
		List<Review> reviews = new ArrayList<>();
		for (ReviewDAO rev : reviewDaos) {
			reviews.add(reviewInfoToReviewForm.convert(rev));
		}
		return reviews;
	}

	@Override
	public List<Review> getReviewsByPage(String prodId, Pageable pageable) {
		List<ReviewDAO> reviewDaos = reviewRepo.findByProductId(prodId, pageable);
		List<Review> reviews = new ArrayList<>();
		for (ReviewDAO rev : reviewDaos) {
			reviews.add(reviewInfoToReviewForm.convert(rev));
		}
		return reviews;
	}

	@Override
	@Transactional
	public void registerReviewLikes(String reviewId) {
		log.info("Register review like service start");
		ReviewDAO reviewDao = reviewRepo.findOne(reviewId);
		log.info(reviewDao.getTitle());
		log.info(String.valueOf(reviewDao.getLikes()));
		if (Objects.isNull(reviewDao)) {
			throw new BusinessValidationException("Error Review doesn't exist");
		}
		long like_cnt = reviewDao.getLikes();
		reviewDao.setLikes(like_cnt + 1);
		reviewRepo.save(reviewDao);
	}
}
