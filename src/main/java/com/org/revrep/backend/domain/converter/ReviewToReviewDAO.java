package com.org.revrep.backend.domain.converter;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.org.revrep.backend.domain.dao.ReviewDAO;
import com.org.revrep.web.domain.Review;

@Component
public class ReviewToReviewDAO implements Converter<Review, ReviewDAO>{

    @Override
    public ReviewDAO convert(Review review) {
        ReviewDAO reviewDAO = new ReviewDAO();
        if (review.getId() != null  && !StringUtils.isEmpty(review.getId())) {
            reviewDAO.set_id(new ObjectId(review.getId()));
        }
        reviewDAO.setProductId(review.getProductId());
        reviewDAO.setUserId(review.getUserId());
        reviewDAO.setTitle(review.getTitle());
        reviewDAO.setDescription(review.getDescription());        
        reviewDAO.setRating(review.getRating());
        reviewDAO.setCreationTime(new Date());
        reviewDAO.setLikes(review.getLikes());
        return reviewDAO;
    }
}
