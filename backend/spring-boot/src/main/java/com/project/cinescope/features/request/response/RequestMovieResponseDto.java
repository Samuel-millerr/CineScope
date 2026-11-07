package com.project.cinescope.features.request.response;

import com.project.cinescope.features.movie.response.MovieResponseDto;
import com.project.cinescope.features.request.Request;
import com.project.cinescope.features.request.enums.RequestStatus;
import com.project.cinescope.features.request.enums.RequestType;
import com.project.cinescope.features.user.response.UserResponseDto;

import java.time.LocalDate;

public record RequestMovieResponseDto(
        Integer id,
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
