package com.project.cinescope.application.user;

import com.project.cinescope.application.request.RequestService;
import com.project.cinescope.application.request.response.RequestMovieResponseDto;
import com.project.cinescope.application.review.ReviewService;
import com.project.cinescope.application.review.response.ReviewResponseDto;
import com.project.cinescope.application.user.response.UserResponseDto;
import com.project.cinescope.core.config.ApiEndpoints;
import com.project.cinescope.core.health.HealthCheckService;
import com.project.cinescope.core.health.response.HealthCheckResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiEndpoints.Users.BASE)
public class UserController {

    private final UserService userService;
    private final RequestService requestService;
    private final ReviewService reviewService;
    private final HealthCheckService healthCheckService;

    public UserController(UserService userService, RequestService requestService, ReviewService reviewService, HealthCheckService healthCheckService) {
        this.userService = userService;
        this.requestService = requestService;
        this.reviewService = reviewService;
        this.healthCheckService = healthCheckService;
    }

    @GetMapping("/health")
    public ResponseEntity<HealthCheckResponseDto> health() {
        return ResponseEntity.ok(healthCheckService.healthCheck(this.getClass()));
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAll() {
        List<UserResponseDto> responseDtoList = userService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(responseDtoList);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<UserResponseDto> getById(@PathVariable Long id) {
        UserResponseDto responseDto = userService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserResponseDto> getByUsername(@PathVariable String username) {
        UserResponseDto responseDto = userService.getByUsername(username);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> findCurrentUser() {
        UserResponseDto responseDto = userService.findCurrentUser();
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @GetMapping("/me/requests")
    public ResponseEntity<List<RequestMovieResponseDto>> findCurrentUserRequests() {
        List<RequestMovieResponseDto> responseDtoList = requestService.findRequestsByCurrentUser();
        return ResponseEntity.status(HttpStatus.OK).body(responseDtoList);
    }

    @GetMapping("/me/reviews")
    public ResponseEntity<List<ReviewResponseDto>> findCurrentUserReviews() {
        List<ReviewResponseDto> responseDtoList = reviewService.findReviewsByCurrentUser();
        return ResponseEntity.status(HttpStatus.OK).body(responseDtoList);
    }

    @GetMapping("/{id}/requests")
    public ResponseEntity<List<RequestMovieResponseDto>> findRequestsByUserId(@PathVariable Long id) {
        List<RequestMovieResponseDto> responseDtoList = requestService.findRequestsByUserId(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseDtoList);
    }

    @GetMapping("/{id}/reviews")
    public ResponseEntity<List<ReviewResponseDto>> findReviewsByUserId(@PathVariable Long id) {
        List<ReviewResponseDto> responseDtoList = reviewService.findReviewsByUserId(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseDtoList);
    }

    @DeleteMapping("/me/requests/{requestId}")
    public ResponseEntity<Void> deleteRequestByCurrentUser(@PathVariable Long requestId) {
        requestService.deleteRequestByCurrentUser(requestId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/me/reviews/{reviewId}")
    public ResponseEntity<Void> deleteReviewByCurrentUser(@PathVariable Long reviewId) {
        reviewService.deleteReviewByCurrentUser(reviewId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
