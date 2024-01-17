package com.simplon.labxpert.exception.handler;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.nio.file.Path;
import java.time.LocalDateTime;
@Getter
@Setter
public class CustomErrorResponse {
    private LocalDateTime timestamp;
    private HttpStatus status;
    private String error;
}
