package com.project.cinescope.application.review.response;

import com.project.cinescope.application.movie.response.MovieResponseDto;
import com.project.cinescope.application.review.Review;
import com.project.cinescope.application.user.response.UserResponseDto;

import java.time.LocalDate;

public record ReviewResponseDto(
        Integer id,
        UserResponseDto user,
        MovieResponseDto movie,
        String reviewText,
        Double reviewRating,
        LocalDate reviewDate
) {
    public static ReviewResponseDto toResponseDto(Review review) {
        return new ReviewResponseDto(
                review.getId(),
                UserResponseDto.toResponseDto(review.getUser()),
                MovieResponseDto.toResponseDto(review.getMovie()),
                review.getReviewText(),
                review.getReviewRating(),
                review.getReviewDate()
        );
    }
}
