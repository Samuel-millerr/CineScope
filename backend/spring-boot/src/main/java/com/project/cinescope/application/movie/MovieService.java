package com.project.cinescope.application.movie;

import com.project.cinescope.application.movie.request.MovieCreateRequestDto;
import com.project.cinescope.application.movie.request.MovieUpdateRequestDto;
import com.project.cinescope.application.movie.response.MovieResponseDto;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface MovieService {
    List<MovieResponseDto> getAll();

    MovieResponseDto getById(Long id);

    MovieResponseDto post(MovieCreateRequestDto requestDto);

    MovieResponseDto patch(Long id, MovieUpdateRequestDto requestDto) throws IllegalAccessException, IntrospectionException, InvocationTargetException;

    void delete(Long id);
}
