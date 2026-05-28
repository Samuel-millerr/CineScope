package com.project.cinescope.movie;

import com.project.cinescope.shared.health.HealthCheckService;
import com.project.cinescope.shared.health.response.HealthCheckResponseDto;
import com.project.cinescope.movie.request.MovieRequestDto;
import com.project.cinescope.movie.response.MovieResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
    private final MovieService movieService;
    private final HealthCheckService healthCheckService;

    public MovieController(MovieService movieService, HealthCheckService healthCheckService) {
        this.movieService = movieService;
        this.healthCheckService = healthCheckService;
    }

    @GetMapping("/health")
    public ResponseEntity<HealthCheckResponseDto> health() {
        return ResponseEntity.ok(healthCheckService.healthCheck(this.getClass()));
    }

    @GetMapping
    public ResponseEntity<List<MovieResponseDto>> getAll() {
        List<MovieResponseDto> responseDtoList = movieService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(responseDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponseDto> getById(
            @PathVariable Long id
    ) {
        MovieResponseDto responseDto = movieService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @PostMapping
    public ResponseEntity<MovieResponseDto> post(
            @RequestBody @Valid MovieRequestDto requestDto
    ) {
        MovieResponseDto responseDto = movieService.post(requestDto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(responseDto.id())
                .toUri();

        return ResponseEntity.created(uri).body(responseDto);
    }
}
