package com.project.cinescope.application.review.request;

public record ReviewRequestDto(
        Long userId,
        Long movieId,
        String reviewText,
        Float reviewRating
) {
}
