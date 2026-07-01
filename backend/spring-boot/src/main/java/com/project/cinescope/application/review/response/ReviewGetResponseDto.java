package com.project.cinescope.application.review.response;

import com.project.cinescope.application.movie.response.MovieResponseDto;
import com.project.cinescope.application.review.Review;
import com.project.cinescope.application.user.response.UserResponseDto;

import java.time.LocalDateTime;

public record ReviewGetResponseDto(
        Long id,
        UserResponseDto user,
        MovieResponseDto movie,
        String reviewText,
        Float reviewRatting,
        LocalDateTime reviewDate
) {
    public static ReviewGetResponseDto toReviewDto(Review review) {
        return new ReviewGetResponseDto(
                review.getId(),
                UserResponseDto.toUserDto(review.getUser()),
                MovieResponseDto.toMovieDto(review.getMovie()),
                review.getReviewText(),
                review.getReviewRatting(),
                review.getReviewDate()
        );
    }
}
