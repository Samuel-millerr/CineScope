package com.project.cinescope.application.review;

import com.project.cinescope.application.review.request.ReviewRequestDto;
import com.project.cinescope.application.review.response.ReviewResponseDto;

import java.util.List;

public interface ReviewService {
    List<ReviewResponseDto> getAll();

    ReviewResponseDto getById(Long id);

    List<ReviewResponseDto> findReviewsByCurrentUser();

    List<ReviewResponseDto> findReviewsByUserId(Long userId);

    ReviewResponseDto post(ReviewRequestDto requestDto);

    void delete(Long id);

    void deleteReviewByCurrentUser(Long id);
}
