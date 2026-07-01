package com.project.cinescope.application.actor.response;

import com.project.cinescope.application.actor.Actor;

public record ActorResponseDto(Long id, String name, String photo) {
    public static ActorResponseDto toActorDto(Actor actor) {
        return new ActorResponseDto(
                actor.getId(),
                actor.getName(),
                actor.getPhoto()
        );
    }
}
