package com.simplon.labxpert.exception.customException;

import org.springframework.http.HttpStatus;

public class CustomNotFoundException extends RuntimeException {

    private final HttpStatus status;

    public CustomNotFoundException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }

}