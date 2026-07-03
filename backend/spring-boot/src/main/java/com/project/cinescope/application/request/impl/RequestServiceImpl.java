package com.project.cinescope.application.request.impl;

import com.project.cinescope.application.movie.Movie;
import com.project.cinescope.application.movie.MovieRepository;
import com.project.cinescope.application.request.Request;
import com.project.cinescope.application.request.RequestRepository;
import com.project.cinescope.application.request.RequestService;
import com.project.cinescope.application.request.request.RequestMovieRequestDto;
import com.project.cinescope.application.request.response.RequestMovieResponseDto;
import com.project.cinescope.application.user.User;
import com.project.cinescope.application.user.UserRepository;
import com.project.cinescope.core.exception.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;

    public RequestServiceImpl(RequestRepository requestRepository, UserRepository userRepository, MovieRepository movieRepository) {
        this.requestRepository = requestRepository;
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
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

    public RequestMovieResponseDto post(RequestMovieRequestDto requestDto) {
        User user = userRepository.findById(requestDto.userId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + requestDto.userId()));

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
}
