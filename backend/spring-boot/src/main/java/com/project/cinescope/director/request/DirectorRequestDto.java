package com.project.cinescope.director.request;

import com.project.cinescope.actor.Actor;
import com.project.cinescope.director.Director;

public record DirectorRequestDto(String name) {
    public static Director toDirector(DirectorRequestDto directorRequestDto) {
        Director director = new Director();
        director.setName(directorRequestDto.name);
        return director;
    }
}
