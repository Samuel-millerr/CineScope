package com.project.cinescope.application.request.response;

import com.project.cinescope.application.movie.response.MovieResponseDto;
import com.project.cinescope.application.request.Request;
import com.project.cinescope.application.request.enums.RequestStatus;
import com.project.cinescope.application.request.enums.RequestType;
import com.project.cinescope.application.user.response.UserResponseDto;

import java.time.LocalDate;

public record RequestMovieResponseDto(
        Long id,
        UserResponseDto user,
        MovieResponseDto movie,
        RequestType requestType,
        RequestStatus requestStatus,
        String requestBody,
        String comment,
        LocalDate requestDate
) {
    public static RequestMovieResponseDto toResponseDto(Request request) {
        return new RequestMovieResponseDto(
                request.getId(),
                UserResponseDto.toResponseDto(request.getUser()),
                request.getMovie() != null? MovieResponseDto.toResponseDto(request.getMovie()) : null,
                request.getType(),
                request.getStatus(),
                request.getRequestBody(),
                request.getComment(),
                request.getRequestDate()
        );
    }
}
