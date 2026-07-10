package com.project.cinescope.application.review.impl;

import com.project.cinescope.application.auth.service.AuthenticatedUserService;
import com.project.cinescope.application.movie.Movie;
import com.project.cinescope.application.movie.MovieRepository;
import com.project.cinescope.application.review.Review;
import com.project.cinescope.application.review.ReviewRepository;
import com.project.cinescope.application.review.ReviewService;
import com.project.cinescope.application.review.request.ReviewRequestDto;
import com.project.cinescope.application.review.response.ReviewResponseDto;
import com.project.cinescope.application.user.User;
import com.project.cinescope.application.user.UserRepository;
import com.project.cinescope.core.exception.exceptions.ForbiddenOperationException;
import com.project.cinescope.core.exception.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;
    private final AuthenticatedUserService authenticatedUserService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, UserRepository userRepository, MovieRepository movieRepository, AuthenticatedUserService authenticatedUserService) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
        this.authenticatedUserService = authenticatedUserService;
    }

    public List<ReviewResponseDto> getAll() {
        List<Review> reviewList = reviewRepository.findAll();
        return reviewList.stream()
                .map(ReviewResponseDto::toResponseDto)
                .toList();
    }

    public ReviewResponseDto getById(Long id) {
        return reviewRepository.findById(id)
                .map(ReviewResponseDto::toResponseDto)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found with id " + id));
    }

    public List<ReviewResponseDto> findReviewsByCurrentUser() {
        User user = authenticatedUserService.getCurrentUser();
        List<Review> reviewList = reviewRepository.findByUser(user);
        return reviewList.stream()
                .map(ReviewResponseDto::toResponseDto)
                .toList();
    }

    public List<ReviewResponseDto> findReviewsByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        List<Review> reviewList = reviewRepository.findByUser(user);
        return reviewList.stream()
                .map(ReviewResponseDto::toResponseDto)
                .toList();
    }

    public ReviewResponseDto post(ReviewRequestDto requestDto) {
        User user = authenticatedUserService.getCurrentUser();

        Movie movie = movieRepository.findById(requestDto.movieId())
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: " + requestDto.movieId()));

        Review review = ReviewRequestDto.toEntity(requestDto);
        review.setUser(user);
        review.setMovie(movie);
        Review createdReview = reviewRepository.save(review);
        return ReviewResponseDto.toResponseDto(createdReview);
    }

    public void delete(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found with id: " + id));

        reviewRepository.delete(review);
    }

    public void deleteReviewByCurrentUser(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found with id: " + id));

        User user = authenticatedUserService.getCurrentUser();

        if (user != review.getUser()) {
            throw new ForbiddenOperationException("You don't have permission to delete another user's reviews.");
        }
        reviewRepository.delete(review);
    }
}
