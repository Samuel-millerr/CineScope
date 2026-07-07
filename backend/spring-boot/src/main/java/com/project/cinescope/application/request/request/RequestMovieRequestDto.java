package com.project.cinescope.application.request.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.cinescope.application.movie.request.MovieRequestDto;
import com.project.cinescope.application.request.Request;
import com.project.cinescope.application.request.enums.RequestType;

public record RequestMovieRequestDto(
        Long userId,
        Long movieId,
        RequestType requestType,
        MovieRequestDto requestBody,
        String comment
) {
    public static final ObjectMapper MAPPER = new ObjectMapper();

    public static Request toRequest(RequestMovieRequestDto requestMovieRequestDto) {
        Request request = new Request();
        request.setType(requestMovieRequestDto.requestType());
        try {
            request.setRequestBody(MAPPER.writeValueAsString(requestMovieRequestDto.requestBody()));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        request.setComment(requestMovieRequestDto.comment());
        return request;
    }
}
