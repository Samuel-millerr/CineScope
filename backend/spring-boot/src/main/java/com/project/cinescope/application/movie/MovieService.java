package com.project.cinescope.application.movie;

import com.project.cinescope.application.movie.request.MovieRequestDto;
import com.project.cinescope.application.movie.response.MovieResponseDto;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface MovieService {
    List<MovieResponseDto> getAll();

    MovieResponseDto getById(Long id);

    MovieResponseDto post(MovieRequestDto requestDto);

    MovieResponseDto patch(Long id, MovieRequestDto requestDto) throws IllegalAccessException, IntrospectionException, InvocationTargetException;

    void delete(Long id);
}
