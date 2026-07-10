package com.project.cinescope.application.review.request;

import com.project.cinescope.application.review.Review;

public record ReviewRequestDto(
        Long movieId,
        String reviewText,
        Float reviewRating
) {
    public static Review toEntity(ReviewRequestDto reviewRequestDto) {
        Review review = new Review();
        review.setReviewText(reviewRequestDto.reviewText());
        review.setReviewRating(reviewRequestDto.reviewRating());
        return review;
    }
}
