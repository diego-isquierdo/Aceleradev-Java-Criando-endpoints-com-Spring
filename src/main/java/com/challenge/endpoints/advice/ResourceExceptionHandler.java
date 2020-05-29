package com.challenge.endpoints.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorReport> handle(ResourceNotFoundException exception){
        ErrorReport error = new ErrorReport(HttpStatus.NOT_FOUND.value(), exception.getMessage(), LocalDateTime.now());

        return ResponseEntity.status(error.getStatus()).body(error);
    }

}
