package com.project.cinescope.features.genre.response;

import com.project.cinescope.features.genre.Genre;

public record GenreResponseDTO(Integer id, String genre) {
    @Deprecated
    public static GenreResponseDTO toResponseDto(Genre genre) {
        return new GenreResponseDTO(
                genre.getId(),
                genre.getGenre()
        );
    }
}
