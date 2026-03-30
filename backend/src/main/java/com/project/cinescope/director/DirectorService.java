package com.project.cinescope.director;

import com.project.cinescope.director.response.DirectorResponseDto;

import java.util.List;

public interface DirectorService {
    List<DirectorResponseDto> getAll();

    DirectorResponseDto getById(Long id);
}
