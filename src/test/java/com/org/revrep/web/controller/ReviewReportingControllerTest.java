package com.org.revrep.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.org.revrep.web.domain.Review;
import com.org.revrep.web.domain.ReviewTest;
import com.org.revrep.web.rest.ReviewReporting;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(controllers = ReviewReporting.class)
public class ReviewReportingControllerTest {

	@Autowired
	private MockMvc			  mockMvc;

	@MockBean
	ReviewReportingController reviewReporting;

	@Test(expected = Test.None.class)
	public void addReport_Successfully_201() throws Exception {

		Mockito.when(reviewReporting.addNewReport(Mockito.any(Review.class))).thenReturn(
			ResponseEntity.status(201).body(ReviewTest.getVALID_INPUT()));

		this.mockMvc
			.perform(post("/reviews").contentType(MediaType.APPLICATION_JSON_UTF8).content(ReviewTest.with_valid_input()))
			.andDo(print())
			.andExpect(status().is(HttpStatus.CREATED.value()));
	}

	@Test(expected = Test.None.class)
	public void failed_With_Spring_validation_Error_400() throws Exception {
		this.mockMvc
			.perform(post("/reviews").contentType(MediaType.APPLICATION_JSON_UTF8).content(ReviewTest.with_in_valid_input()))
			.andDo(print())
			.andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
	}
}
