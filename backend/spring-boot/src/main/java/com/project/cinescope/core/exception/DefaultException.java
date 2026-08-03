package com.project.cinescope.core.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class DefaultException extends RuntimeException{

    private final HttpStatus status;

    public DefaultException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return null;
    }
}
