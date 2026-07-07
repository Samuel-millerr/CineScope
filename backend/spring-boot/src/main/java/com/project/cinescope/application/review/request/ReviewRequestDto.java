package com.project.cinescope.application.review.request;

import com.project.cinescope.application.review.Review;
import com.project.cinescope.application.user.User;

public record ReviewRequestDto(
        Long movieId,
        String reviewText,
        Float reviewRating
) {
    public static Review toReview(ReviewRequestDto reviewRequestDto) {
        Review review = new Review();
        review.setReviewText(reviewRequestDto.reviewText());
        review.setReviewRating(reviewRequestDto.reviewRating());
        return review;
    }
}
