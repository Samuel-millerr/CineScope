package com.project.cinescope.actor;

import com.project.cinescope.actor.response.ActorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
