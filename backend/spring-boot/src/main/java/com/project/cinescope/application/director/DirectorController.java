package com.project.cinescope.application.director;

import com.project.cinescope.application.director.request.DirectorRequestDto;
import com.project.cinescope.application.director.response.DirectorResponseDto;
import com.project.cinescope.core.config.ApiEndpoints;
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
    public ResponseEntity<List<DirectorResponseDto>> getAll() {
        List<DirectorResponseDto> responseDtoList = directorService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(responseDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DirectorResponseDto> getById(
            @PathVariable Long id
    ) {
        DirectorResponseDto responseDto = directorService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @PostMapping
    public ResponseEntity<DirectorResponseDto> post(
            @RequestBody @Valid DirectorRequestDto requestDto
    ) {
        DirectorResponseDto responseDto = directorService.post(requestDto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(responseDto.id())
                .toUri();

        return ResponseEntity.created(uri).body(responseDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DirectorResponseDto> patch(
            @PathVariable Long id,
            @RequestBody @Valid DirectorRequestDto requestDto
    ) {
        DirectorResponseDto responseDto = directorService.patch(id, requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ) {
        directorService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
