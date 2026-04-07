package com.project.cinescope.review.response;

import com.project.cinescope.movie.response.MovieGetResponseDto;
import com.project.cinescope.review.Review;
import com.project.cinescope.user.response.UserResponseDto;

import java.time.LocalDateTime;

public record ReviewGetResponseDto(
        Long id,
        UserResponseDto user,
        MovieGetResponseDto movie,
        String reviewText,
        Float reviewRatting,
        LocalDateTime reviewDate
) {
    public static ReviewGetResponseDto toReviewDto(Review review) {
        return new ReviewGetResponseDto(
                review.getId(),
                UserResponseDto.toUserDto(review.getUser()),
                MovieGetResponseDto.toMovieDto(review.getMovie()),
                review.getReviewText(),
                review.getReviewRatting(),
                review.getReviewDate()
        );
    }
}
