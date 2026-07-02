package com.project.cinescope.application.movie;

import com.project.cinescope.application.movie.request.MovieRequestDto;
import com.project.cinescope.application.movie.response.MovieResponseDto;
import com.project.cinescope.core.config.ApiEndpoints;
import com.project.cinescope.core.health.HealthCheckService;
import com.project.cinescope.core.health.response.HealthCheckResponseDto;
import jakarta.validation.Valid;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(ApiEndpoints.Movies.BASE)
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

    @SneakyThrows
    @PatchMapping("/{id}")
    public ResponseEntity<MovieResponseDto> patch(
            @PathVariable Long id,
            @RequestBody @Valid MovieRequestDto requestDto
    ) {
        MovieResponseDto movieResponseDto = movieService.patch(id, requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(movieResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MovieResponseDto> delete(
            @PathVariable Long id
    ) {
        movieService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
