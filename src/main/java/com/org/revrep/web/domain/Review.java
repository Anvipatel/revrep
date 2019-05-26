package com.org.revrep.web.domain;

import java.util.Date;

import javax.validation.constraints.NotNull;

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
	private String id;
	private String productId;
	private String userId;
	
	@NotEmpty(message = "Missing title.")
	private String title;

	private String description;

	@NotNull(message = "Please provide rating.")
	private Double rating;

	private Date creationTime;
	
	private long likes = 0L;
}
