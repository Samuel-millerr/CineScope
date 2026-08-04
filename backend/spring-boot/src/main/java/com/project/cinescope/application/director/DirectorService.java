package com.project.cinescope.application.director;

import com.project.cinescope.application.director.request.DirectorRequestDto;
import com.project.cinescope.application.director.response.DirectorResponseDto;

import java.util.List;

public interface DirectorService {
    List<DirectorResponseDto> getAll();

    DirectorResponseDto getById(Integer id);

    DirectorResponseDto post(DirectorRequestDto requestDto);

    DirectorResponseDto patch(Integer id, DirectorRequestDto requestDto);

    void delete(Integer id);
}
