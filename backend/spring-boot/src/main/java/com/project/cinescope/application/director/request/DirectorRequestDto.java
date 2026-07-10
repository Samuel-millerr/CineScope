package com.project.cinescope.application.director.request;

import com.project.cinescope.application.director.Director;

public record DirectorRequestDto(String name) {
    public static Director toEntity(DirectorRequestDto directorRequestDto) {
        Director director = new Director();
        director.setName(directorRequestDto.name);
        return director;
    }
}
