package com.project.cinescope.director;

import com.project.cinescope.director.response.DirectorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        DirectorResponseDto directorResponseDto = directorService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(directorResponseDto);
    }
}
