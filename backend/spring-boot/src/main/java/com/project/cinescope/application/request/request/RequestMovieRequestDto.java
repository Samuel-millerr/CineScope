package com.project.cinescope.application.request.request;

import com.project.cinescope.application.movie.request.MovieRequestDto;
import com.project.cinescope.application.request.Request;
import com.project.cinescope.application.request.enums.RequestStatus;
import com.project.cinescope.application.request.enums.RequestType;

public record RequestMovieRequestDto(
        Long userId,
        Long movieId,
        RequestType requestType,
        RequestStatus requestStatus,
        MovieRequestDto movieRequestDto,
        String comment
) {
    public static Request toRequest(RequestMovieRequestDto requestMovieRequestDto) {
        Request request = new Request();
        request.setType(requestMovieRequestDto.requestType());
        request.setStatus(requestMovieRequestDto.requestStatus());
        request.setRequestBody(requestMovieRequestDto.movieRequestDto());
        request.setComment(requestMovieRequestDto.comment());
        return request;
    }
}
