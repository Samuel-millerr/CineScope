package com.project.cinescope.actor;

import com.project.cinescope.actor.request.ActorRequestDto;
import com.project.cinescope.actor.response.ActorResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/actors")
public class ActorController {
    private final ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
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
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ActorResponseDto> delete(
            @PathVariable Long id
    ) {
        actorService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
