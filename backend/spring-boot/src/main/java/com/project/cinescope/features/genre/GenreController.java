package com.project.cinescope.features.genre;

import com.project.cinescope.features.genre.request.GenreRequestDTO;
import com.project.cinescope.features.genre.response.GenreResponseDTO;
import com.project.cinescope.core.utils.endpoints.ApiEndpoints;
import com.project.cinescope.core.health.HealthCheckService;
import com.project.cinescope.core.health.response.HealthCheckResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiEndpoints.Genres.BASE)
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
    public ResponseEntity<List<GenreResponseDTO>> getAll() {
        List<GenreResponseDTO> response = genreService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenreResponseDTO> getById(
            @PathVariable Integer id
    ) {
        GenreResponseDTO response = genreService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<GenreResponseDTO> post(
            @RequestBody @Valid GenreRequestDTO request
    ) {
        GenreResponseDTO response = genreService.post(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        genreService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
