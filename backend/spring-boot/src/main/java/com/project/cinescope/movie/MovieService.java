package com.project.cinescope.movie;

import com.project.cinescope.movie.request.MovieRequestDto;
import com.project.cinescope.movie.response.MovieResponseDto;

import java.util.List;

public interface MovieService {
    List<MovieResponseDto> getAll();

    MovieResponseDto getById(Long id);

    MovieResponseDto post(MovieRequestDto requestDto);
}
