package com.project.cinescope.features.actor;

import com.project.cinescope.features.actor.request.ActorRequestDto;
import com.project.cinescope.features.actor.response.ActorResponseDto;

import java.util.List;

public interface ActorService {
    List<ActorResponseDto> getAll();

    ActorResponseDto getById(Integer id);

    ActorResponseDto post(ActorRequestDto requestDto);

    ActorResponseDto patch(Integer id, ActorRequestDto requestDto);

    void delete(Integer id);
}
