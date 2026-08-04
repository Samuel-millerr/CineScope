package com.project.cinescope.application.director.response;

import com.project.cinescope.application.director.Director;

public record DirectorResponseDto(Integer id, String name) {
    public static DirectorResponseDto toResponseDto(Director director) {
        return new DirectorResponseDto(
                director.getId(),
                director.getName()
        );
    }
}
