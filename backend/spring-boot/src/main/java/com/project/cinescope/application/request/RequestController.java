package com.project.cinescope.application.request;

import com.project.cinescope.application.request.request.RequestMovieRequestDto;
import com.project.cinescope.application.request.response.RequestMovieResponseDto;
import com.project.cinescope.core.config.ApiEndpoints;
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

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
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
                .buildAndExpand()
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
