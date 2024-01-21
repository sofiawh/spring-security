package com.simplon.labxpert.exception.handler;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    // TODO : TO @ayoub ait si ahmad MAKE A MEANING PACKAGE FOR THIS CLASS
    // TODO : TO @ayoub ait si ahmad ADD JAVA DOC
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<CustomErrorResponse> handleDataIntegrityViolation(DataIntegrityViolationException e) {
        CustomErrorResponse errors = new CustomErrorResponse();
        errors.setTimestamp(LocalDateTime.now());
        errors.setError(e.getMessage());
        errors.setStatus(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<CustomErrorResponse> handleValidationExceptions(ConstraintViolationException ex) {
        CustomErrorResponse errors = new CustomErrorResponse();
        errors.setTimestamp(LocalDateTime.now());
        errors.setError(ex.getMessage());
        errors.setStatus(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    // TODO : TO @chaimaa mahy THIS GLOBAL EXCEPTION HANDLER IS NOT WORKING
    // TODO : TO @chaimaa mahy CHECK IF THIS IS THE RIGHT WAY TO HANDLE THE EXCEPTIONS I SUGESTE TO READ THIS ARTICLE: https://dev.to/tienbku/global-exception-handler-in-spring-boot-3mbp
    // TODO : TO @chaimaa mahy ADD JAVA DOC
    /*
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<CustomErrorResponse> handleResourceNotFoundException(ResponseStatusException ex) {
        CustomErrorResponse errors = new CustomErrorResponse();
        errors.setStatus(HttpStatus.NOT_FOUND);
        errors.setError(ex.getReason());
        errors.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);

    }

     */
}