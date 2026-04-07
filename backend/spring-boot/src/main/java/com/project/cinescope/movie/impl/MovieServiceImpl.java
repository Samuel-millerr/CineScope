package com.project.cinescope.movie.impl;

import com.project.cinescope.exception.ResourceNotFoundException;
import com.project.cinescope.movie.Movie;
import com.project.cinescope.movie.MovieRepository;
import com.project.cinescope.movie.MovieService;
import com.project.cinescope.movie.response.MovieGetResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<MovieGetResponseDto> getAll() {
        List<Movie> movieList = movieRepository.findAll();
        return movieList.stream()
                .map(MovieGetResponseDto::toMovieDto)
                .toList();
    }

    public MovieGetResponseDto getById(Long id) {
        return movieRepository.findById(id)
                .map(MovieGetResponseDto::toMovieDto)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id " + id));
    }
}
