package com.org.revrep.interfaces.controller;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.org.revrep.web.domain.Review;

public interface IReviewController {
	public ResponseEntity<Review> addNewReport(@RequestBody @Validated Review review);
	public ResponseEntity<List<Review>>	getReviews(@RequestParam(value="productId") String productId);
	public void registerReviewLikes(String reviewId);
	ResponseEntity<List<Review>> getReviewsByPage(String prodId, Pageable pageable);
}
