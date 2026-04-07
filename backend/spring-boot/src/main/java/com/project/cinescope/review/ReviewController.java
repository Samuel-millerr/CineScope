package com.project.cinescope.review;

import com.project.cinescope.review.response.ReviewGetResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<List<ReviewGetResponseDto>> getAll() {
        List<ReviewGetResponseDto> responseDtoList = reviewService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(responseDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewGetResponseDto> getById(
            @PathVariable Long id) {
        ReviewGetResponseDto responseDto = reviewService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
