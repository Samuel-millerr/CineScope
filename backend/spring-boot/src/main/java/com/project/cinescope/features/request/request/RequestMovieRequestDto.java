package com.project.cinescope.features.request.request;

import com.project.cinescope.features.request.Request;
import com.project.cinescope.features.request.enums.RequestType;
import tools.jackson.databind.JsonNode;

public record RequestMovieRequestDto(
        Integer userId,
        Integer movieId,
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
