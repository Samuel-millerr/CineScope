package com.project.cinescope.application.review;

import com.project.cinescope.application.review.request.ReviewRequestDto;
import com.project.cinescope.application.review.response.ReviewResponseDto;

import java.util.List;

public interface ReviewService {
    List<ReviewResponseDto> getAll();

    ReviewResponseDto getById(Long id);

    ReviewResponseDto post(ReviewRequestDto requestDto);

    void delete(Long id);
}
