package com.project.cinescope.movie;

import com.project.cinescope.movie.response.MovieGetResponseDto;

import java.util.List;

public interface MovieService {
    public List<MovieGetResponseDto> getAll();

    public MovieGetResponseDto getById(Long id);
}
