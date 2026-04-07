package com.project.cinescope.director.response;

import com.project.cinescope.director.Director;

public record DirectorResponseDto(Long id, String name) {
    public static DirectorResponseDto toDirectorDto(Director director) {
        return new DirectorResponseDto(
                director.getId(),
                director.getName()
        );
    }
}
