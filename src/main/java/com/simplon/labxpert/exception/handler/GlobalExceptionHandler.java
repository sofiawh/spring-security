package com.simplon.labxpert.exception.handler;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(
            MethodArgumentNotValidException ex, WebRequest request) {
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

    // TODO : @Chaimaa : ADD A EXCEPTION HANDLER FOR RESOURCE NOT FOUND EXCEPTION
    // TODO : @Chaimaa : NWARIHA SCREENSHOT BILA TA 7AJA MAKHAS TKOUN HKK KOLXE KHAS ITHANDLA

}