package com.project.cinescope.movie.response;

import com.project.cinescope.actor.Actor;
import com.project.cinescope.director.Director;
import com.project.cinescope.movie.Movie;

import java.util.List;

public record MovieGetResponseDto(
        Long id,
        String title,
        Integer duration,
        Integer publicationYear,
        String synopsis,
        String poster,
        List<Actor> actors,
        List<Director> directors
) {
    public static MovieGetResponseDto toMovieDto(Movie movie) {
        return new MovieGetResponseDto(
                movie.getId(),
                movie.getTitle(),
                movie.getDuration(),
                movie.getPublicationYear(),
                movie.getSynopsis(),
                movie.getPoster(),
                movie.getActors(),
                movie.getDirectors()
        );
    }
}
