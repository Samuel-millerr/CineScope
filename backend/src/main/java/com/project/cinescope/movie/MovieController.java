package com.project.cinescope.movie;

import com.project.cinescope.movie.response.MovieGetResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<List<MovieGetResponseDto>> getAll() {
        List<MovieGetResponseDto> responseDtoList = movieService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(responseDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieGetResponseDto> getById(
            @PathVariable Long id
    ) {
        MovieGetResponseDto responseDto = movieService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

}
