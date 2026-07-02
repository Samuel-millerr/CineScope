package com.project.cinescope.application.actor;

import com.project.cinescope.application.actor.request.ActorRequestDto;
import com.project.cinescope.application.actor.response.ActorResponseDto;

import java.util.List;

public interface ActorService {
    List<ActorResponseDto> getAll();

    ActorResponseDto getById(Long id);

    ActorResponseDto post(ActorRequestDto requestDto);

    ActorResponseDto patch(Long id, ActorRequestDto requestDto);

    void delete(Long id);
}
