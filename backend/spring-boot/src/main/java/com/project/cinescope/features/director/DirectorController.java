package com.project.cinescope.features.director;

import com.project.cinescope.features.director.request.DirectorRequestDTO;
import com.project.cinescope.features.director.response.DirectorResponseDTO;
import com.project.cinescope.core.utils.endpoints.ApiEndpoints;
import com.project.cinescope.core.health.HealthCheckService;
import com.project.cinescope.core.health.response.HealthCheckResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(ApiEndpoints.Directors.BASE)
public class DirectorController {

    private final DirectorService directorService;
    private final HealthCheckService healthCheckService;

    public DirectorController(DirectorService directorService, HealthCheckService healthCheckService) {
        this.directorService = directorService;
        this.healthCheckService = healthCheckService;
    }

    @GetMapping("/health")
    public ResponseEntity<HealthCheckResponseDto> health() {
        return ResponseEntity.ok(healthCheckService.healthCheck(this.getClass()));
    }

    @GetMapping
    public ResponseEntity<List<DirectorResponseDTO>> getAll() {
        List<DirectorResponseDTO> response = directorService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DirectorResponseDTO> getById(
            @PathVariable Integer id
    ) {
        DirectorResponseDTO response = directorService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<DirectorResponseDTO> post(
            @RequestBody @Valid DirectorRequestDTO request
    ) {
        DirectorResponseDTO response = directorService.post(request);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(uri).body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DirectorResponseDTO> patch(
            @PathVariable Integer id,
            @RequestBody @Valid DirectorRequestDTO request
    ) {
        DirectorResponseDTO response = directorService.patch(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Integer id
    ) {
        directorService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
