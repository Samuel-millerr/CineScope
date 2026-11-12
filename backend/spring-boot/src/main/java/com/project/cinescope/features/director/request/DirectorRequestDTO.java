package com.project.cinescope.features.director.request;

import com.project.cinescope.features.director.Director;
import jakarta.validation.constraints.NotBlank;

public record DirectorRequestDTO(
        @NotBlank String name
) {
    @Deprecated
    public static Director toEntity(DirectorRequestDTO directorRequestDto) {
        Director director = new Director();
        director.setName(directorRequestDto.name);
        return director;
    }
}
