package com.project.cinescope.application.request;

import com.project.cinescope.application.request.request.RequestMovieRequestDto;
import com.project.cinescope.application.request.response.RequestMovieResponseDto;

import java.util.List;

public interface RequestService {
    List<RequestMovieResponseDto> getAll();

    RequestMovieResponseDto getById(Long id);

    List<RequestMovieResponseDto> findRequestsByCurrentUser();

    List<RequestMovieResponseDto> findRequestsByUserId(Long userId);

    RequestMovieResponseDto post(RequestMovieRequestDto requestDto);

    void delete(Long id);

    void deleteRequestByCurrentUser(Long id);
}
