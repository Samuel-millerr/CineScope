package com.project.cinescope.features.actor.response;

import com.project.cinescope.features.actor.Actor;

public record ActorResponseDTO(Integer id, String name, String photo) {
    @Deprecated
    public static ActorResponseDTO toResponseDto(Actor actor) {
        return new ActorResponseDTO(
                actor.getId(),
                actor.getName(),
                actor.getPhoto()
        );
    }
}
