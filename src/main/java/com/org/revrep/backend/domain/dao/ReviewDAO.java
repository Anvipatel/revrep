package com.org.revrep.backend.domain.dao;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Document(collection="Review")
public class ReviewDAO  implements IDomain {


	@Id	
	private ObjectId _id;	
	private String productId;	
	private String userId;
	private String title;
	private String description;
	private Double rating;
	private Date creationTime;
	private long likes;	
}
