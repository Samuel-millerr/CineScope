package com.project.cinescope.application.actor.request;

import com.project.cinescope.application.actor.Actor;

public record ActorRequestDto(String name, String photo) {
    public static Actor toEntity(ActorRequestDto actorRequestDto) {
        Actor actor = new Actor();
        actor.setName(actorRequestDto.name());
        actor.setPhoto(actorRequestDto.photo());
        return actor;
    }
}