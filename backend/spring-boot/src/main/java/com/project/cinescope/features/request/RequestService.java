package com.project.cinescope.features.request;

import com.project.cinescope.features.request.enums.RequestStatus;
import com.project.cinescope.features.request.request.RequestMovieRequestDto;
import com.project.cinescope.features.request.response.RequestMovieResponseDto;

import java.util.List;

public interface RequestService {
    List<RequestMovieResponseDto> getAll();

    RequestMovieResponseDto getById(Integer id);

    List<RequestMovieResponseDto> findRequestsByCurrentUser();

    List<RequestMovieResponseDto> findRequestsByUserId(Integer userId);

    RequestMovieResponseDto post(RequestMovieRequestDto requestDto);

    RequestMovieResponseDto updateStatus(Integer id, RequestStatus requestStatus);

    void delete(Integer id);

    void deleteRequestByCurrentUser(Integer id);
}
