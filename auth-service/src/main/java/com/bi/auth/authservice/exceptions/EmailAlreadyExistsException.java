package com.bi.auth.authservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class EmailAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = -8368554904231687626L;

	public EmailAlreadyExistsException(String message) {
		super(message);
	}

}
