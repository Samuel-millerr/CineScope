package com.project.cinescope.application.review;

import com.project.cinescope.application.review.request.ReviewRequestDto;
import com.project.cinescope.application.review.response.ReviewResponseDto;
import com.project.cinescope.core.config.ApiEndpoints;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiEndpoints.Reviews.BASE)
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<List<ReviewResponseDto>> getAll() {
        List<ReviewResponseDto> responseDtoList = reviewService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(responseDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewResponseDto> getById(
            @PathVariable Long id
    ) {
        ReviewResponseDto responseDto = reviewService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @PostMapping
    public ResponseEntity<ReviewResponseDto> post(
            @RequestBody @Valid ReviewRequestDto reviewRequestDto
    ) {
        ReviewResponseDto responseDto = reviewService.post(reviewRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    };

    @DeleteMapping("/{id}")
    public ResponseEntity<ReviewResponseDto> delete(
            @PathVariable Long id
    ) {
        reviewService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
