package com.project.cinescope.features.movie;

import com.project.cinescope.features.movie.request.MovieCreateRequestDto;
import com.project.cinescope.features.movie.request.MovieUpdateRequestDto;
import com.project.cinescope.features.movie.response.MovieResponseDto;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface MovieService {
    List<MovieResponseDto> getAll();

    MovieResponseDto getById(Integer id);

    MovieResponseDto post(MovieCreateRequestDto requestDto);

    MovieResponseDto patch(Integer id, MovieUpdateRequestDto requestDto) throws IllegalAccessException, IntrospectionException, InvocationTargetException;

    void delete(Integer id);
}
