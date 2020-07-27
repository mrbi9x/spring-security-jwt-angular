package com.bi.auth.authservice.exceptions;

import java.util.Collections;
import java.util.Date;

import com.bi.auth.authservice.payloads.ApiErrorsResponse;
import com.bi.auth.authservice.payloads.ErrorResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(value = {UsernameAlreadyExistsException.class, EmailAlreadyExistsException.class})
    public final ResponseEntity<?> handlerAlreadyExistsException(RuntimeException ex, WebRequest request) {
        Date now = new Date();
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, now, ex.getMessage(), request.getDescription(false));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiErrorsResponse.builder().errors(Collections.singletonList(errorResponse)).build());
    }
}