package com.project.cinescope.application.review.request;

import com.project.cinescope.application.review.Review;

public record ReviewRequestDto(
        Integer movieId,
        String reviewText,
        Double reviewRating
) {
    public static Review toEntity(ReviewRequestDto reviewRequestDto) {
        Review review = new Review();
        review.setReviewText(reviewRequestDto.reviewText());
        review.setReviewRating(reviewRequestDto.reviewRating());
        return review;
    }
}
