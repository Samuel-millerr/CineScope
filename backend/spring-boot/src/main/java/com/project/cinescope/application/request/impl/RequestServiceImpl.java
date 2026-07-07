package com.project.cinescope.application.request.impl;

import com.project.cinescope.application.auth.service.AuthenticatedUserService;
import com.project.cinescope.application.movie.Movie;
import com.project.cinescope.application.movie.MovieRepository;
import com.project.cinescope.application.request.Request;
import com.project.cinescope.application.request.RequestRepository;
import com.project.cinescope.application.request.RequestService;
import com.project.cinescope.application.request.request.RequestMovieRequestDto;
import com.project.cinescope.application.request.response.RequestMovieResponseDto;
import com.project.cinescope.application.user.User;
import com.project.cinescope.application.user.UserRepository;
import com.project.cinescope.core.exception.exceptions.ForbiddenOperationException;
import com.project.cinescope.core.exception.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;
    private final AuthenticatedUserService authenticatedUserService;

    public RequestServiceImpl(RequestRepository requestRepository, MovieRepository movieRepository, UserRepository userRepository, AuthenticatedUserService authenticatedUserService) {
        this.requestRepository = requestRepository;
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
        this.authenticatedUserService = authenticatedUserService;
    }

    public List<RequestMovieResponseDto> getAll() {
        List<Request> requestList = requestRepository.findAll();
        return requestList.stream()
                .map(RequestMovieResponseDto::toRequestDto)
                .toList();
    }

    public RequestMovieResponseDto getById(Long id) {
        return requestRepository.findById(id)
                .map(RequestMovieResponseDto::toRequestDto)
                .orElseThrow(() -> new ResourceNotFoundException("Request not found with id: " + id));
    }

    public List<RequestMovieResponseDto> findRequestsByCurrentUser() {
        User user = authenticatedUserService.getCurrentUser();
        List<Request> requestList = requestRepository.findByUser(user);
        return requestList.stream()
                .map(RequestMovieResponseDto::toRequestDto)
                .toList();
    }

    public List<RequestMovieResponseDto> findRequestsByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        List<Request> requestList = requestRepository.findByUser(user);
        return requestList.stream()
                .map(RequestMovieResponseDto::toRequestDto)
                .toList();
    }

    public RequestMovieResponseDto post(RequestMovieRequestDto requestDto) {
        User user = authenticatedUserService.getCurrentUser();

        Movie movie = movieRepository.findById(requestDto.movieId())
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: " + requestDto.movieId()));

        Request request = RequestMovieRequestDto.toRequest(requestDto);
        request.setUser(user);
        request.setMovie(movie);
        Request createdRequest = requestRepository.save(request);
        return RequestMovieResponseDto.toRequestDto(createdRequest);
    }

    public void delete(Long id) {
        Request request = requestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Request not found with id: " + id));

        requestRepository.delete(request);
    }

    public void deleteRequestByCurrentUser(Long id) {
        User user = authenticatedUserService.getCurrentUser();

        Request request = requestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Request not found with id: " + id));

        if (user != request.getUser()) {
            throw new ForbiddenOperationException("You don't have permission to delete another user's requests.");
        }
        requestRepository.delete(request);
    }
}
