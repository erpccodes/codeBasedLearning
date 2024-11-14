package com.example.Journal.exception;

import org.springframework.http.HttpStatus;

public class CustomApplicationException extends RuntimeException{

	 private final HttpStatus status;

	    public CustomApplicationException(String message, HttpStatus status) {
	        super(message);
	        this.status = status;
	    }

	    public HttpStatus getStatus() {
	        return status;
	    }
}
