package com.project.cinescope.actor;

import com.project.cinescope.actor.request.ActorRequestDto;
import com.project.cinescope.actor.response.ActorResponseDto;
import com.project.cinescope.shared.health.HealthCheckService;
import com.project.cinescope.shared.health.response.HealthCheckResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/actors")
public class ActorController {

    private final ActorService actorService;
    private final HealthCheckService healthCheckService;

    public ActorController(ActorService actorService, HealthCheckService healthCheckService) {
        this.actorService = actorService;
        this.healthCheckService = healthCheckService;
    }

    @GetMapping("/health")
    public ResponseEntity<HealthCheckResponseDto> healthCheck() {
        return ResponseEntity.ok(healthCheckService.healthCheck(this.getClass()));
    }

    @GetMapping
    public ResponseEntity<List<ActorResponseDto>> getAll() {
        List<ActorResponseDto> responseDtoList = actorService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(responseDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActorResponseDto> getById(
            @PathVariable Long id
    ) {
        ActorResponseDto responseDto = actorService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @PostMapping
    public ResponseEntity<ActorResponseDto> post(
            @RequestBody @Valid ActorRequestDto requestDto
    ) {
        ActorResponseDto responseDto = actorService.post(requestDto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(responseDto.id())
                .toUri();

        return ResponseEntity.created(uri).body(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ActorResponseDto> delete(
            @PathVariable Long id
    ) {
        actorService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
