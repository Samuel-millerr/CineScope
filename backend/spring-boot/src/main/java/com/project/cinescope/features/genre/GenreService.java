package com.project.cinescope.features.genre;

import com.project.cinescope.features.genre.request.GenreRequestDto;
import com.project.cinescope.features.genre.response.GenreResponseDto;

import java.util.List;

public interface GenreService {
    List<GenreResponseDto> getAll();

    GenreResponseDto getById(Integer id);

    GenreResponseDto post(GenreRequestDto requestDto);

    void delete(Integer id);
}
