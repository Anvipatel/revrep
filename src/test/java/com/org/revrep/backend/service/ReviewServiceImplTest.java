package com.org.revrep.backend.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.org.revrep.backend.domain.converter.ReviewToReviewDAO;
import com.org.revrep.backend.domain.converter.ReviewDAOToReview;
import com.org.revrep.backend.domain.dao.ReviewDAO;
import com.org.revrep.backend.repository.ReviewRepo;
import com.org.revrep.web.domain.Review;
import com.org.revrep.web.domain.ReviewTest;
import com.org.revrep.web.exception.BusinessValidationException;

@RunWith(SpringJUnit4ClassRunner.class)
public class ReviewServiceImplTest {

	@Mock
	private ReviewRepo			   reviewRepo;

	private ReviewToReviewDAO reviewFormToReviewInfo = new ReviewToReviewDAO();

	private ReviewDAOToReview reviewInfoToReviewForm = new ReviewDAOToReview();

	private ReviewServiceImpl	   reviewService		  = null;

	@Before
	public void init() {
		this.reviewService = new ReviewServiceImpl(reviewRepo, reviewFormToReviewInfo, reviewInfoToReviewForm);
	}

	@Test(expected = BusinessValidationException.class)
	public void addNewReview_WhenNull_IsPassed() {
		reviewService.addNewReview(null);
	}

	@Test(expected = Test.None.class)
	public void addNewReview_When_Valid_data_Is_Passed() throws JsonParseException, JsonMappingException, IOException {
		ReviewDAO dbReview = getReviewDaoSample();

		Mockito.when(reviewRepo.save(Mockito.any(ReviewDAO.class))).thenReturn(dbReview);
		Review parsedData = reviewService.addNewReview(ReviewTest.getVALID_INPUT());

		assertNotNull(parsedData);
		assertEquals(dbReview.get_id().toHexString(), parsedData.getId());
		Mockito.verify(reviewRepo, Mockito.times(1)).save(Mockito.any(ReviewDAO.class));
	}

	@Test(expected = Test.None.class)
	public void get_List_Of_reviews() throws JsonParseException, JsonMappingException, IOException {
		ReviewDAO dbReview = getReviewDaoSample();

		Mockito.when(reviewRepo.findByProductId(Mockito.matches("any-id"))).thenReturn(Arrays.asList(dbReview));
		List<Review> parsedData = reviewService.getReviews("any-id");

		assertNotNull(parsedData);
		assertEquals(1, parsedData.size());
		Mockito.verify(reviewRepo, Mockito.times(1)).findByProductId(Mockito.matches("any-id"));
	}

	private ReviewDAO getReviewDaoSample() {
		ReviewDAO dbReviewnew = new ReviewDAO();
		dbReviewnew.set_id(ObjectId.get());
		dbReviewnew.setLikes(4);
		dbReviewnew.setRating(4.5D);
		return dbReviewnew;
	}
}
