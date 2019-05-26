package com.org.revrep.web.exception;

import org.springframework.http.HttpStatus;

/**
 * {@link HttpStatus#is4xxClientError()}
 * @author ANVIP
 *
 */
public class BusinessValidationException extends EndUserException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3527499391373551071L;

	public BusinessValidationException(String message) {
		super(message);
	}

}
