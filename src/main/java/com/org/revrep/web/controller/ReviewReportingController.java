package com.org.revrep.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.org.revrep.backend.events.entity.NewReviewAdded;
import com.org.revrep.interfaces.controller.IReviewController;
import com.org.revrep.interfaces.service.IReviewService;
import com.org.revrep.web.domain.Review;

/**
 * @author Anvi
 *
 */
@Controller
public class ReviewReportingController implements IReviewController {

	@Autowired
	private IReviewService reviewService;

	@Autowired
	private ApplicationEventPublisher eventPublisher;

	@Override
	public ResponseEntity<Review> addNewReport(Review review) {
		Review newReview = reviewService.addNewReview(review);

		publishForNewReview(newReview);
		return ResponseEntity.status(HttpStatus.CREATED).body(newReview);
	}

	private void publishForNewReview(Review review) {
		// a separate application event publisher which will deal with sync/async
		// operations
		eventPublisher.publishEvent(new NewReviewAdded(review));
	}

	@Override
	public ResponseEntity<List<Review>> getReviews(String productId) {
		List<Review> reviewList = reviewService.getReviews(productId);
		return ResponseEntity.ok().body(reviewList);
	}

	@Override
	public ResponseEntity<List<Review>> getReviewsByPage(String prodId, Pageable pageable) {
		List<Review> reviewList = reviewService.getReviewsByPage(prodId, pageable);
		return ResponseEntity.ok().body(reviewList);
	}

	@Override
	public void registerReviewLikes(String reviewId) {
		reviewService.registerReviewLikes(reviewId);
	}
}
