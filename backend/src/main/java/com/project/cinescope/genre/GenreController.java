package com.project.cinescope.genre;

import com.project.cinescope.genre.response.GenreResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/genres")
public class GenreController {
    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    public ResponseEntity<List<GenreResponseDto>> getAll() {
        List<GenreResponseDto> responseDtoList = genreService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(responseDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenreResponseDto> getById(
            @PathVariable Long id
    ) {
        GenreResponseDto responseDto = genreService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
