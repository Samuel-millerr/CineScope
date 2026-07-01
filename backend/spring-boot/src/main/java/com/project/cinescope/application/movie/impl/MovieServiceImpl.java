package com.project.cinescope.application.movie.impl;

import com.project.cinescope.application.movie.Movie;
import com.project.cinescope.application.movie.MovieRepository;
import com.project.cinescope.application.movie.MovieService;
import com.project.cinescope.application.movie.request.MovieRequestDto;
import com.project.cinescope.application.movie.response.MovieResponseDto;
import com.project.cinescope.core.exception.exceptions.DuplicateResourceException;
import com.project.cinescope.core.exception.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<MovieResponseDto> getAll() {
        List<Movie> movieList = movieRepository.findAll();
        return movieList.stream()
                .map(MovieResponseDto::toMovieDto)
                .toList();
    }

    public MovieResponseDto getById(Long id) {
        return movieRepository.findById(id)
                .map(MovieResponseDto::toMovieDto)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id " + id));
    }

    public MovieResponseDto post(MovieRequestDto requestDto) {
        String movieTitle = requestDto.title();
        if (movieRepository.existsByTitle(movieTitle)) {
            throw new DuplicateResourceException("Movie with title " + movieTitle + " already exists");
        }

        Movie movie = MovieRequestDto.toMovie(requestDto);
        Movie createdMovie = movieRepository.save(movie);
        return MovieResponseDto.toMovieDto(createdMovie);
    }
}
