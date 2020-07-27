package com.bi.auth.authservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class UsernameAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = -3668727285317769016L;

	public UsernameAlreadyExistsException(String message) {
		super(message);
	}

}
