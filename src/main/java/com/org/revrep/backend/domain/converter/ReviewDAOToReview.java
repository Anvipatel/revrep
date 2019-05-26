package com.org.revrep.backend.domain.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.org.revrep.backend.domain.dao.ReviewDAO;
import com.org.revrep.web.domain.Review;

@Component
public class ReviewDAOToReview implements Converter<ReviewDAO, Review>{

	@Override
	public Review convert(ReviewDAO reviewDAO) {
        Review review = new Review();
        review.setId(reviewDAO.get_id().toHexString());
        review.setProductId(reviewDAO.getProductId());
        review.setUserId(reviewDAO.getUserId());
        review.setDescription(reviewDAO.getDescription());
        review.setRating(reviewDAO.getRating());
        review.setTitle(reviewDAO.getTitle());
        review.setCreationTime(reviewDAO.getCreationTime());
        review.setLikes(reviewDAO.getLikes());
        return review;
	}

}
