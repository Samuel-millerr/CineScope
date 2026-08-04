package com.project.cinescope.core.exception;

import com.project.cinescope.core.exception.exceptions.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DefaultException.class)
    public ResponseEntity<StandardError> handleDefaultException(DefaultException e, HttpServletRequest request) {
        StandardError error = new StandardError(
                Instant.now(),
                e.getStatus().value(),
                "Default Exception",
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(e.getStatus()).body(error);
    }
}
