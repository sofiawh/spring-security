package com.simplon.labxpert.exception.customException;

import com.simplon.labxpert.exception.customException.CustomNotFoundException;
import com.simplon.labxpert.exception.handler.CustomErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CustomNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleCustomNotFoundException(CustomNotFoundException ex) {
        CustomErrorResponse response = new CustomErrorResponse();
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(ex.getStatus());
        response.setError(ex.getMessage());

        return new ResponseEntity<>(response, ex.getStatus());
    }
}