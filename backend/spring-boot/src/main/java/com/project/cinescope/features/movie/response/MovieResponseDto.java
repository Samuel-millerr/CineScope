package com.project.cinescope.features.movie.response;

import com.project.cinescope.features.actor.response.ActorResponseDto;
import com.project.cinescope.features.director.response.DirectorResponseDto;
import com.project.cinescope.features.movie.Movie;

import java.util.List;

public record MovieResponseDto(
        Integer id,
        String title,
        Integer duration,
        Integer publicationYear,
        String synopsis,
        String poster,
        List<ActorResponseDto> actors,
        List<DirectorResponseDto> directors
) {
    public static MovieResponseDto toResponseDto(Movie movie) {
        return new MovieResponseDto(
                movie.getId(),
                movie.getTitle(),
                movie.getDuration(),
                movie.getPublicationYear(),
                movie.getSynopsis(),
                movie.getPoster(),
                movie.getActors().stream()
                        .map(ActorResponseDto::toResponseDto)
                        .toList(),
                movie.getDirectors().stream()
                        .map(DirectorResponseDto::toResponseDto)
                        .toList()
        );
    }
}
