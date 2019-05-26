package com.org.revrep.web.exception;

/**
 * All user's end specific exception should extends given class.
 * @author ANVIP
 *
 */
public abstract class EndUserException extends RuntimeException {

	public EndUserException(String message) {
		super(message);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -828094340952560913L;

}
