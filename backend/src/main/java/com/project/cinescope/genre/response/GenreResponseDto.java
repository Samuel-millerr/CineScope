package com.project.cinescope.genre.response;

import com.project.cinescope.genre.Genre;

public record GenreResponseDto(Long id, String genre) {
    public static GenreResponseDto toGenreDto(Genre genre) {
        return new GenreResponseDto(
                genre.getId(),
                genre.getGenre()
        );
    }
}
