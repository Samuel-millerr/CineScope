package com.project.cinescope.application.review.impl;

import com.project.cinescope.application.movie.Movie;
import com.project.cinescope.application.movie.MovieRepository;
import com.project.cinescope.application.review.Review;
import com.project.cinescope.application.review.ReviewRepository;
import com.project.cinescope.application.review.ReviewService;
import com.project.cinescope.application.review.request.ReviewRequestDto;
import com.project.cinescope.application.review.response.ReviewResponseDto;
import com.project.cinescope.application.user.User;
import com.project.cinescope.application.user.UserRepository;
import com.project.cinescope.core.exception.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository, UserRepository userRepository, MovieRepository movieRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
    }

    public List<ReviewResponseDto> getAll() {
        List<Review> reviewList = reviewRepository.findAll();
        return reviewList.stream()
                .map(ReviewResponseDto::toReviewDto)
                .toList();
    }

    public ReviewResponseDto getById(Long id) {
        return reviewRepository.findById(id)
                .map(ReviewResponseDto::toReviewDto)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found with id " + id));
    }

    public ReviewResponseDto post(ReviewRequestDto requestDto) {
        User user = userRepository.findById(requestDto.userId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + requestDto.userId()));
        Movie movie = movieRepository.findById(requestDto.movieId())
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: " + requestDto.movieId()));

        Review review = new Review();
        review.setUser(user);
        review.setMovie(movie);
        review.setReviewText(requestDto.reviewText());
        review.setReviewRating(requestDto.reviewRating());
        System.out.println("Rating: " + requestDto.reviewRating());
        System.out.println("Entity Rating: " + review.getReviewRating());
        Review createdReview = reviewRepository.save(review);
        return ReviewResponseDto.toReviewDto(createdReview);
    }

    public void delete(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found with id: " + id));

        reviewRepository.delete(review);
    }
}
