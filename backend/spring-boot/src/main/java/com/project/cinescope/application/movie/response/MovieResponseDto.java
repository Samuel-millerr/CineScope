package com.project.cinescope.application.movie.response;

import com.project.cinescope.application.actor.response.ActorResponseDto;
import com.project.cinescope.application.director.response.DirectorResponseDto;
import com.project.cinescope.application.movie.Movie;

import java.util.List;

public record MovieResponseDto(
        Long id,
        String title,
        Integer duration,
        Integer publicationYear,
        String synopsis,
        String poster,
        List<ActorResponseDto> actors,
        List<DirectorResponseDto> directors
) {
    public static MovieResponseDto toMovieDto(Movie movie) {
        return new MovieResponseDto(
                movie.getId(),
                movie.getTitle(),
                movie.getDuration(),
                movie.getPublicationYear(),
                movie.getSynopsis(),
                movie.getPoster(),
                movie.getActors().stream()
                        .map(ActorResponseDto::toActorDto)
                        .toList(),
                movie.getDirectors().stream()
                        .map(DirectorResponseDto::toDirectorDto)
                        .toList()
        );
    }
}
