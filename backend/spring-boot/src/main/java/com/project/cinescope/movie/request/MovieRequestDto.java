package com.project.cinescope.movie.request;

import com.project.cinescope.movie.Movie;

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
