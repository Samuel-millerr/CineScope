package com.project.cinescope.exception;

import com.project.cinescope.exception.exceptions.DuplicateResourceException;
import com.project.cinescope.exception.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        StandardError error = new StandardError(
                Instant.now(),
                HttpStatus.NOT_FOUND.value(),
                "Resource not found",
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<StandardError> entityDuplicate(DuplicateResourceException e, HttpServletRequest request) {
        StandardError error = new StandardError(
                Instant.now(),
                HttpStatus.CONFLICT.value(),
                "Resource already exists",
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }
}
