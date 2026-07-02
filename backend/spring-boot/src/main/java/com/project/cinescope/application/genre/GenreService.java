package com.project.cinescope.application.genre;

import com.project.cinescope.application.genre.request.GenreRequestDto;
import com.project.cinescope.application.genre.response.GenreResponseDto;

import java.util.List;

public interface GenreService {
    List<GenreResponseDto> getAll();

    GenreResponseDto getById(Long id);

    GenreResponseDto post(GenreRequestDto requestDto);

    void delete(Long id);
}
