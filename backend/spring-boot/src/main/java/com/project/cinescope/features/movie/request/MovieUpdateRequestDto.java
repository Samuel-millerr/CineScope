package com.project.cinescope.features.movie.request;

import com.project.cinescope.features.movie.Movie;

public record MovieUpdateRequestDto(
        String title,
        Integer duration,
        Integer publicationYear,
        String synopsis,
        String poster
) {
    public static Movie toEntity(MovieUpdateRequestDto movieRequestDto) {
        Movie movie = new Movie();
        movie.setTitle(movieRequestDto.title());
        movie.setDuration(movieRequestDto.duration());
        movie.setPublicationYear(movieRequestDto.publicationYear());
        movie.setSynopsis(movieRequestDto.synopsis());
        movie.setPoster(movieRequestDto.poster());
        return movie;
    }
}
