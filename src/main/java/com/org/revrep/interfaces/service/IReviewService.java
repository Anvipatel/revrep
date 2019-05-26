package com.org.revrep.interfaces.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.org.revrep.web.domain.Review;

public interface IReviewService {
	public Review addNewReview(Review review);
	
	//Review updateReview(Review review);
	
	//Review deleteReview(Review review);
		
	public List<Review> getReviews(String prodId);
	
	public void registerReviewLikes(String reviewId);

	List<Review> getReviewsByPage(String prodId, Pageable pageable);
}
