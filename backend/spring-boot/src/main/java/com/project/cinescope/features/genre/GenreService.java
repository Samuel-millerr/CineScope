package com.project.cinescope.features.genre;

import com.project.cinescope.features.genre.request.GenreRequestDTO;
import com.project.cinescope.features.genre.response.GenreResponseDTO;

import java.util.List;

public interface GenreService {
    List<GenreResponseDTO> getAll();

    GenreResponseDTO getById(Integer id);

    GenreResponseDTO post(GenreRequestDTO request);

    void delete(Integer id);
}
