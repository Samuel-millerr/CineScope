package com.project.cinescope.application.review;

import com.project.cinescope.application.review.response.ReviewGetResponseDto;

import java.util.List;

public interface ReviewService {
    List<ReviewGetResponseDto> getAll();

    ReviewGetResponseDto getById(Long id);
}
