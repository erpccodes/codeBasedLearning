package com.example.Journal.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	 @ExceptionHandler(CustomApplicationException.class)
	    public ResponseEntity<String> handleCustomException(CustomApplicationException ex) {
	        return new ResponseEntity<>(ex.getMessage(), ex.getStatus());
	    }
}