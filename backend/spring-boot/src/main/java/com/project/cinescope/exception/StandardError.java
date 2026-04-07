package com.project.cinescope.exception;

import java.time.Instant;

public record StandardError(
        Instant timesatamp,
        Integer status,
        String error,
        String message,
        String path) {
}
