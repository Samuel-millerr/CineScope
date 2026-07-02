package com.project.cinescope.application.genre.request;

import com.project.cinescope.application.genre.Genre;

public record GenreRequestDto(String genre) {
    public static Genre toGenre(GenreRequestDto genreRequestDto) {
        Genre genre = new Genre();
        genre.setGenre(genreRequestDto.genre);
        return genre;
    }
}
