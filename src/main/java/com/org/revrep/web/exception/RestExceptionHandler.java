package com.org.revrep.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Exception leader that handles all type of {@link ExceptionHandler} supported class and present them for API response.
 * 
 * @Example
 * 
 * @author ANVIP
 */
@RestControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(EndUserException.class)
	public ResponseEntity<ApiError> handlerUserException(EndUserException u) {
		return new ResponseEntity<ApiError>(new ApiError(u.getLocalizedMessage(),HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiError> handlerUserException(MethodArgumentNotValidException u) {
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, u.getBindingResult().getAllErrors());
		return new ResponseEntity<ApiError>(apiError, HttpStatus.BAD_REQUEST);
	}
	
}
