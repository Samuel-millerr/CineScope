package com.project.cinescope.health.response;

import java.time.Instant;
import java.util.List;
import java.util.Map;

public record HealthCheckResponseDto(
        String status,
        String component,
        Integer routesQtd,
        List<Map<String, String>> routes,
        Instant timestamp
) {
}
