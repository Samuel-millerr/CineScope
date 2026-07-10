package com.project.cinescope.application.request.request;

import com.project.cinescope.application.request.Request;
import com.project.cinescope.application.request.enums.RequestType;
import tools.jackson.databind.JsonNode;

public record RequestMovieRequestDto(
        Long userId,
        Long movieId,
        RequestType requestType,
        JsonNode requestBody,
        String comment
) {
    public static Request toEntity(RequestMovieRequestDto requestMovieRequestDto) {
        Request request = new Request();
        request.setType(requestMovieRequestDto.requestType());
        request.setComment(requestMovieRequestDto.comment());
        return request;
    }
}
