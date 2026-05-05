package com.project.cinescope.actor.request;

import com.project.cinescope.actor.Actor;

public record ActorRequestDto(String name, String photo) {
    public static Actor toActor(ActorRequestDto actorRequestDto) {
        Actor actor = new Actor();
        actor.setName(actorRequestDto.name());
        actor.setPhoto(actorRequestDto.photo());
        return actor;
    }
}