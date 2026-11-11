package com.project.cinescope.features.actor.request;

import jakarta.validation.constraints.NotBlank;

public record ActorRequestDTO(
        @NotBlank String name,
        @NotBlank String photo
) {
}