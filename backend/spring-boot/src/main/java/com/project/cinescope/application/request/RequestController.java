package com.project.cinescope.application.request;

import com.project.cinescope.application.auth.service.AuthenticatedUserService;
import com.project.cinescope.application.request.request.RequestMovieRequestDto;
import com.project.cinescope.application.request.response.RequestMovieResponseDto;
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
@RequestMapping(ApiEndpoints.Requests.BASE)
public class RequestController {
    private final RequestService requestService;
    private final HealthCheckService healthCheckService;

    public RequestController(RequestService requestService, HealthCheckService healthCheckService, AuthenticatedUserService authenticatedUserService) {
        this.requestService = requestService;
        this.healthCheckService = healthCheckService;
    }

    @GetMapping("/health")
    public ResponseEntity<HealthCheckResponseDto> health() {
        return ResponseEntity.ok(healthCheckService.healthCheck(this.getClass()));
    }

    @GetMapping
    public ResponseEntity<List<RequestMovieResponseDto>> getAll() {
        List<RequestMovieResponseDto> responseDtoList = requestService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(responseDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequestMovieResponseDto> getById(
            @PathVariable Long id
    ) {
        RequestMovieResponseDto responseDto = requestService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @PostMapping
    public ResponseEntity<RequestMovieResponseDto> post(
            @RequestBody @Valid RequestMovieRequestDto requestDto
    ) {
        RequestMovieResponseDto responseDto = requestService.post(requestDto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(responseDto.id())
                .toUri();

        return ResponseEntity.created(uri).body(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ) {
        requestService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
