package com.org.revrep.web.domain;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.org.revrep.backend.util.ObjectMapperUtil;

@RunWith(SpringJUnit4ClassRunner.class)
public class ReviewTest {

	private static final String	VALID_INPUT		= "{\"id\":\"5ce7eb69936cca42f706ab61\",\"productId\":\"222\",\"userId\":\"11212-2232\",\"title\":\"Good Product\",\"description\":\"Quality is good, durable. Go for it.\",\"rating\":4.5}";

	private static final String	_IN_VALID_INPUT	= "{}";

	private Validator			validator;

	@Before
	public void init() {
		this.validator = Validation.buildDefaultValidatorFactory().getValidator();
	}

	@Test
	public void validateNumber_When_valid_data_isPassed() throws JsonParseException, JsonMappingException, IOException {
		Review review = ObjectMapperUtil.toObject(VALID_INPUT, Review.class);

		Set<ConstraintViolation<Review>> constraints = validator.validate(review);

		assertEquals(0, constraints.size());
	}

	@Test
	public void validateNumber_When_valid_data_is_INVALID() throws JsonParseException, JsonMappingException, IOException {
		Review review = ObjectMapperUtil.toObject(_IN_VALID_INPUT, Review.class);

		Set<ConstraintViolation<Review>> constraints = validator.validate(review);

		assertEquals(2, constraints.size());
	}

	public static Review get_IN_VALID_INPUT_asObject() throws JsonParseException, JsonMappingException, IOException {
		return ObjectMapperUtil.toObject(_IN_VALID_INPUT, Review.class);
	}

	public static String with_valid_input() {
		return VALID_INPUT;
	}

	public static String with_in_valid_input() {
		return _IN_VALID_INPUT;
	}
	public static Review getVALID_INPUT() throws JsonParseException, JsonMappingException, IOException {
		return ObjectMapperUtil.toObject(VALID_INPUT, Review.class);
	}
}
