package com.org.revrep.web.rest;

import java.net.HttpURLConnection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.org.revrep.interfaces.controller.IReviewController;
import com.org.revrep.web.domain.Review;
import com.org.revrep.web.exception.RestExceptionHandler;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/reviews")
public class ReviewReporting {

	@Autowired
	IReviewController reviewController;

	@ApiOperation(value = "Used to add new Review", response = Review.class)
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Bad Request", response = RestExceptionHandler.class),
			@ApiResponse(code = HttpURLConnection.HTTP_UNAVAILABLE, message = "Service Unavailable", response = RestExceptionHandler.class) })
	@RequestMapping(method = RequestMethod.POST, consumes = { "application/json;charset=UTF-8" }, produces = {
			"application/json;charset=UTF-8" })
	public ResponseEntity<Review> addReview(
			@RequestBody @Validated @ApiParam(value = "A Review object for the add review activity", required = true) Review review) {
		return reviewController.addNewReport(review);

	}

	@ApiOperation(value = "Used to get List of Reviews for a product")
	@GetMapping
	public ResponseEntity<List<Review>> getReviews(
			@RequestParam(value = "productId") @ApiParam(value = "String ProductId", required = true) String productId,
			@RequestParam(value = "page", defaultValue = "0") @ApiParam(value = "Page number", required = false) int page,
			@RequestParam(value = "size", defaultValue = "20") @ApiParam(value = "Number of records", required = false) int size,
			@RequestParam(value = "sort", defaultValue = "creationTime") @ApiParam(value = "Number of records", required = false) String sort,
			@RequestParam(value = "direction", defaultValue = "desc") @ApiParam(value = "Number of records", required = false) String direction) {
		// return reviewController.getReviews(productId);
		Direction d = direction.equalsIgnoreCase("asc") ? Direction.ASC : Direction.DESC;
		Pageable pageable = new PageRequest(page, size, new Sort(d, sort));
		return reviewController.getReviewsByPage(productId, pageable);
	}

	@ApiOperation(value = "Used to register a like sentiment for an existing review")
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Invalid input reviewId", response = RestExceptionHandler.class),
			@ApiResponse(code = HttpURLConnection.HTTP_UNAVAILABLE, message = "Service Unavailable", response = RestExceptionHandler.class) })	
	@PostMapping("/like")
	public ResponseEntity<Void> registerReviewLikes(@RequestParam(value = "reviewId") String reviewId) {
		try {
			reviewController.registerReviewLikes(reviewId);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

	}

}
