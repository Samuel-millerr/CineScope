package com.project.cinescope.features.genre.request;

import com.project.cinescope.features.genre.Genre;

public record GenreRequestDto(String genre) {
    public static Genre toEntity(GenreRequestDto genreRequestDto) {
        Genre genre = new Genre();
        genre.setGenre(genreRequestDto.genre);
        return genre;
    }
}
