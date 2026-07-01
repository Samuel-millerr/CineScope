package com.project.cinescope.application.genre;

import com.project.cinescope.application.genre.response.GenreResponseDto;
import com.project.cinescope.core.health.HealthCheckService;
import com.project.cinescope.core.health.response.HealthCheckResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/genres")
public class GenreController {
    private final GenreService genreService;
    private final HealthCheckService healthCheckService;

    public GenreController(GenreService genreService, HealthCheckService healthCheckService) {
        this.genreService = genreService;
        this.healthCheckService = healthCheckService;
    }

    @GetMapping("/health")
    public ResponseEntity<HealthCheckResponseDto> health() {
        return ResponseEntity.ok(healthCheckService.healthCheck(this.getClass()));
    }

    @GetMapping
    public ResponseEntity<List<GenreResponseDto>> getAll() {
        List<GenreResponseDto> responseDtoList = genreService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(responseDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenreResponseDto> getById(
            @PathVariable Long id
    ) {
        GenreResponseDto responseDto = genreService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
