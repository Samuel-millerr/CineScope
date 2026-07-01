package com.project.cinescope.application.director;

import com.project.cinescope.application.director.request.DirectorRequestDto;
import com.project.cinescope.application.director.response.DirectorResponseDto;

import java.util.List;

public interface DirectorService {
    List<DirectorResponseDto> getAll();

    DirectorResponseDto getById(Long id);

    DirectorResponseDto post(DirectorRequestDto requestDto);

    void delete(Long id);
}
