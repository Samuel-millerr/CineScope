package com.project.cinescope.director;

import com.project.cinescope.director.request.DirectorRequestDto;
import com.project.cinescope.director.response.DirectorResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/directors")
public class DirectorController {
    private final DirectorService directorService;

    public DirectorController(DirectorService directorService) {
        this.directorService = directorService;
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
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DirectorResponseDto> delete(
            @PathVariable Long id
    ) {
        directorService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
