package com.project.cinescope.application.genre.response;

import com.project.cinescope.application.genre.Genre;

public record GenreResponseDto(Long id, String genre) {
    public static GenreResponseDto toGenreDto(Genre genre) {
        return new GenreResponseDto(
                genre.getId(),
                genre.getGenre()
        );
    }
}
