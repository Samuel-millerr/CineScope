package com.project.cinescope.features.actor.request;

import com.project.cinescope.features.actor.Actor;
import jakarta.validation.constraints.NotBlank;

public record ActorRequestDTO(
        @NotBlank String name,
        @NotBlank String photo
) {
    @Deprecated
    public static Actor toEntity(ActorRequestDTO actorRequestDto) {
        Actor actor = new Actor();
        actor.setName(actorRequestDto.name());
        actor.setPhoto(actorRequestDto.photo());
        return actor;
    }
}