package com.project.cinescope.features.director.response;

import com.project.cinescope.features.director.Director;

public record DirectorResponseDTO(Integer id, String name) {
    @Deprecated
    public static DirectorResponseDTO toResponseDto(Director director) {
        return new DirectorResponseDTO(
                director.getId(),
                director.getName()
        );
    }
}
