package com.org.revrep.web.exception;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class BusinessValidationExceptionTest {

	@Test(expected = BusinessValidationException.class)
	public void businessValidationExceptionMsgCheck() {

		String message = "Business Validation Exception";
		BusinessValidationException ex = new BusinessValidationException(message);
		throw ex;
	}
}
