package com.project.cinescope.application.movie.request;

import com.project.cinescope.application.movie.Movie;

public record MovieRequestDto(
        String title,
        Integer duration,
        Integer publicationYear,
        String synopsis,
        String poster
) {
    public static Movie toMovie(MovieRequestDto movieRequestDto) {
        Movie movie = new Movie();
        movie.setTitle(movieRequestDto.title());
        movie.setDuration(movieRequestDto.duration());
        movie.setPublicationYear(movieRequestDto.publicationYear());
        movie.setSynopsis(movieRequestDto.synopsis());
        movie.setPoster(movieRequestDto.poster());
        return movie;
    }
}
