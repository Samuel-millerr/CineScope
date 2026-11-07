package com.project.cinescope.features.genre.response;

import com.project.cinescope.features.genre.Genre;

public record GenreResponseDto(Integer id, String genre) {
    public static GenreResponseDto toResponseDto(Genre genre) {
        return new GenreResponseDto(
                genre.getId(),
                genre.getGenre()
        );
    }
}
