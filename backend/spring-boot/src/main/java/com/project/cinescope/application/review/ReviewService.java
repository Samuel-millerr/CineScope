package com.project.cinescope.application.review;

import com.project.cinescope.application.review.request.ReviewRequestDto;
import com.project.cinescope.application.review.response.ReviewResponseDto;

import java.util.List;

public interface ReviewService {
    List<ReviewResponseDto> getAll();

    ReviewResponseDto getById(Integer id);

    List<ReviewResponseDto> findReviewsByCurrentUser();

    List<ReviewResponseDto> findReviewsByUserId(Integer userId);

    ReviewResponseDto post(ReviewRequestDto requestDto);

    void delete(Integer id);

    void deleteReviewByCurrentUser(Integer id);
}
