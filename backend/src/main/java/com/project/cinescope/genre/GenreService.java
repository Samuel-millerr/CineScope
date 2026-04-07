package com.project.cinescope.genre;

import com.project.cinescope.genre.response.GenreResponseDto;

import java.util.List;

public interface GenreService {
    List<GenreResponseDto> getAll();

    GenreResponseDto getById(Long id);
}
