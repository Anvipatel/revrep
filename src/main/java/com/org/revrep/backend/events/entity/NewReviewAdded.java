package com.org.revrep.backend.events.entity;

import com.org.revrep.web.domain.Review;

public class NewReviewAdded {
	
	public static final String EVENT_NAME = "NewReviewAddedEvent";

	private final Review   reviewInfo;

	public NewReviewAdded(Review review) {
		this.reviewInfo = review;
	}

	public String event() {
		return EVENT_NAME;
	}

	public Review getReviewInfo() {
		return reviewInfo;
	}

}
