package com.project.cinescope.features.director;

import com.project.cinescope.features.director.request.DirectorRequestDto;
import com.project.cinescope.features.director.response.DirectorResponseDto;

import java.util.List;

public interface DirectorService {
    List<DirectorResponseDto> getAll();

    DirectorResponseDto getById(Integer id);

    DirectorResponseDto post(DirectorRequestDto requestDto);

    DirectorResponseDto patch(Integer id, DirectorRequestDto requestDto);

    void delete(Integer id);
}
