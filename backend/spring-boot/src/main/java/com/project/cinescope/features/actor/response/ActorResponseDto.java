package com.project.cinescope.features.actor.response;

import com.project.cinescope.features.actor.Actor;

public record ActorResponseDto(Integer id, String name, String photo) {
    public static ActorResponseDto toResponseDto(Actor actor) {
        return new ActorResponseDto(
                actor.getId(),
                actor.getName(),
                actor.getPhoto()
        );
    }
}
