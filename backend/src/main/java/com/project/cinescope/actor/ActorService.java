package com.project.cinescope.actor;

import com.project.cinescope.actor.response.ActorResponseDto;

import java.util.List;

public interface ActorService {
    List<ActorResponseDto> getAll();

    ActorResponseDto getById(Long id);
}
