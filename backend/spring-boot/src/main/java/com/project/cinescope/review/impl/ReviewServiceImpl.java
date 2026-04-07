package com.project.cinescope.review.impl;

import com.project.cinescope.exception.ResourceNotFoundException;
import com.project.cinescope.review.Review;
import com.project.cinescope.review.ReviewRepository;
import com.project.cinescope.review.ReviewService;
import com.project.cinescope.review.response.ReviewGetResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<ReviewGetResponseDto> getAll() {
        List<Review> reviewList = reviewRepository.findAll();
        return reviewList.stream()
                .map(ReviewGetResponseDto::toReviewDto)
                .toList();
    }

    public ReviewGetResponseDto getById(Long id) {
        return reviewRepository.findById(id)
                .map(ReviewGetResponseDto::toReviewDto)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found with id " + id));
    }
}
