package com.org.revrep.web.domain;

import java.util.Date;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Review {
	@Null(message = "new review reviewId must not be present")
	private String id;
	
	@Size(min = 2, max = 20, message = "productId must be between 2 and 20 characters")
	@NotNull(message = "Please provide productId")
	private String productId;
	
	@Size(min = 2, max = 20, message = "userId must be between 2 and 20 characters")
	@NotNull(message = "Please provide userId")
	private String userId;

	@NotEmpty(message = "Missing title.")
	private String title;

	@Size(min = 10, max = 200, message = "description must be between 10 and 200 characters")
	private String description;

	@NotNull(message = "Please provide rating.")
	@DecimalMin(value = "1.0", message = "rating should be between 1-5")
	@DecimalMax(value = "5.0", message = "rating should be between 1-5")
	private Double rating;

	private Date creationTime;
	
	@Max( value = 0, message = "New review likes count must not be present")
	@Min( value = 0, message = "New review likes count must not be present")
	private long likes = 0L;
}
