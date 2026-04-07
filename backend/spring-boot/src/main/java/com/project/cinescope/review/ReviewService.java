package com.project.cinescope.review;

import com.project.cinescope.review.response.ReviewGetResponseDto;

import java.util.List;

public interface ReviewService {
    List<ReviewGetResponseDto> getAll();

    ReviewGetResponseDto getById(Long id);
}
