package com.org.revrep.web.exception;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author ANVIP
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_EMPTY)
public class ApiError {

	private Set<String>	messages;

	private int			status;

	public ApiError(String errorMessage, HttpStatus httpStatus) {
		setMessages(new HashSet<>(Arrays.asList(errorMessage)));
		setStatus(httpStatus.value());
	}

	public ApiError(HttpStatus badRequest, List<ObjectError> allErrors) {
		this(badRequest);
		addValidationErrors(allErrors);
	}

	public ApiError(HttpStatus badRequest) {
		setStatus(badRequest.value());
	}

	public void addSubError(String subError) {
		if (Objects.isNull(this.messages)) {
			this.messages = new HashSet<>();
		}
		messages.add(subError);
	}

	private void addValidationError(String message) {
		addSubError(message);
	}

	private void addValidationError(ObjectError fieldError) {
		this.addValidationError(fieldError.getDefaultMessage());
	}

	private void addValidationErrors(List<ObjectError> fieldErrors) {
		fieldErrors.forEach(this::addValidationError);
	}

	public Set<String> getMessages() {
		return messages;
	}

	public void setMessages(Set<String> messages) {
		this.messages = messages;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
