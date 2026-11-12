package com.project.cinescope.features.actor;

import com.project.cinescope.core.health.HealthCheckService;
import com.project.cinescope.core.health.response.HealthCheckResponseDto;
import com.project.cinescope.core.utils.endpoints.ApiEndpoints;
import com.project.cinescope.features.actor.request.ActorRequestDTO;
import com.project.cinescope.features.actor.response.ActorResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(ApiEndpoints.Actors.BASE)
public class ActorController {

    private final ActorService actorService;
    private final HealthCheckService healthCheckService;

    public ActorController(ActorService actorService, HealthCheckService healthCheckService) {
        this.actorService = actorService;
        this.healthCheckService = healthCheckService;
    }

    @GetMapping("/health")
    public ResponseEntity<HealthCheckResponseDto> health() {
        return ResponseEntity.ok(healthCheckService.healthCheck(this.getClass()));
    }

    @GetMapping
    public ResponseEntity<List<ActorResponseDTO>> getAll() {
        List<ActorResponseDTO> response = actorService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActorResponseDTO> getById(
            @PathVariable Integer id
    ) {
        ActorResponseDTO response = actorService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<ActorResponseDTO> post(
            @RequestBody @Valid ActorRequestDTO request
    ) {
        ActorResponseDTO response = actorService.post(request);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(uri).body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ActorResponseDTO> patch(
            @PathVariable Integer id,
            @RequestBody @Valid ActorRequestDTO request
    ) {
        ActorResponseDTO response = actorService.patch(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Integer id
    ) {
        actorService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
