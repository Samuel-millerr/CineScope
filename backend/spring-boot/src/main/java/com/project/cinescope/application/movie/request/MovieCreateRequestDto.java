package com.project.cinescope.application.movie.request;

import com.project.cinescope.application.movie.Movie;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MovieCreateRequestDto(
        @NotBlank(message = "O filme não pode ser cadastrado sem nome") String title,
        @NotNull(message = "O filme necessita de uma duração em minutos para ser cadastrado") Integer duration,
        @NotNull(message = "O filme precisa ser cadastrado com uma data de publicação") Integer publicationYear,
        String synopsis,
        String poster
) {
    public static Movie toEntity(MovieCreateRequestDto movieRequestDto) {
        Movie movie = new Movie();
        movie.setTitle(movieRequestDto.title());
        movie.setDuration(movieRequestDto.duration());
        movie.setPublicationYear(movieRequestDto.publicationYear());
        movie.setSynopsis(movieRequestDto.synopsis());
        movie.setPoster(movieRequestDto.poster());
        return movie;
    }
}