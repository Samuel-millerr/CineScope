package com.project.cinescope.features.genre.request;

import com.project.cinescope.features.genre.Genre;
import jakarta.validation.constraints.NotBlank;

public record GenreRequestDTO(
        @NotBlank String genre
) {
    @Deprecated
    public static Genre toEntity(GenreRequestDTO genreRequestDto) {
        Genre genre = new Genre();
        genre.setGenre(genreRequestDto.genre);
        return genre;
    }
}
