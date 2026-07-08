package com.project.cinescope.application.request.impl;

import com.project.cinescope.application.auth.service.AuthenticatedUserService;
import com.project.cinescope.application.movie.Movie;
import com.project.cinescope.application.movie.MovieRepository;
import com.project.cinescope.application.movie.MovieService;
import com.project.cinescope.application.movie.request.MovieCreateRequestDto;
import com.project.cinescope.application.movie.request.MovieUpdateRequestDto;
import com.project.cinescope.application.request.Request;
import com.project.cinescope.application.request.RequestRepository;
import com.project.cinescope.application.request.RequestService;
import com.project.cinescope.application.request.enums.RequestStatus;
import com.project.cinescope.application.request.enums.RequestType;
import com.project.cinescope.application.request.request.RequestMovieRequestDto;
import com.project.cinescope.application.request.response.RequestMovieResponseDto;
import com.project.cinescope.application.user.User;
import com.project.cinescope.application.user.UserRepository;
import com.project.cinescope.core.exception.exceptions.ConstraintViolationException;
import com.project.cinescope.core.exception.exceptions.ForbiddenOperationException;
import com.project.cinescope.core.exception.exceptions.ResourceNotFoundException;
import jakarta.validation.ConstraintDeclarationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.stereotype.Service;
import tools.jackson.core.TreeNode;
import tools.jackson.databind.ObjectMapper;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Set;

@Service
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;
    private final MovieService movieService;
    private final AuthenticatedUserService authenticatedUserService;
    private final ObjectMapper objectMapper;
    private final Validator validator;

    public RequestServiceImpl(RequestRepository requestRepository, MovieRepository movieRepository, UserRepository userRepository, MovieService movieService, AuthenticatedUserService authenticatedUserService, ObjectMapper objectMapper, Validator validator) {
        this.requestRepository = requestRepository;
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
        this.movieService = movieService;
        this.authenticatedUserService = authenticatedUserService;
        this.objectMapper = objectMapper;
        this.validator = validator;
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

        Request request = RequestMovieRequestDto.toRequest(requestDto);
        request.setUser(user);

        Object requestBody;
        switch (requestDto.requestType()) {
            case ADIÇÃO -> {
                MovieCreateRequestDto movieCreateRequestDto = objectMapper.treeToValue(requestDto.requestBody(), MovieCreateRequestDto.class);

                Set<ConstraintViolation<MovieCreateRequestDto>> violations = validator.validate(movieCreateRequestDto);

                if (!violations.isEmpty()) {
                    throw new ConstraintViolationException(violations.toString());
                }

                requestBody = objectMapper.treeToValue(requestDto.requestBody(), MovieCreateRequestDto.class);
            }
            case EDIÇÃO -> {
                Movie movie = movieRepository.findById(requestDto.movieId())
                        .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: " + requestDto.movieId()));

                request.setMovie(movie);

                requestBody = objectMapper.treeToValue(requestDto.requestBody(), MovieUpdateRequestDto.class);
            }
            default -> throw new IllegalArgumentException("Invalid request type");
        }

        request.setRequestBody(objectMapper.writeValueAsString(requestBody));

        Request createdRequest = requestRepository.save(request);
        return RequestMovieResponseDto.toRequestDto(createdRequest);
    }

    public RequestMovieResponseDto updateStatus(
            Long id,
            RequestStatus requestStatus
    ) {
        Request request = requestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Request not found with id: " + id));

        switch (requestStatus) {
            case RequestStatus.APROVADO -> {
                if (request.getType() == RequestType.ADIÇÃO) {

                    MovieCreateRequestDto requestDto = objectMapper.readValue(request.getRequestBody(), MovieCreateRequestDto.class);
                    movieService.post(requestDto);

                } else if (request.getType() == RequestType.EDIÇÃO) {
                    try {

                        MovieUpdateRequestDto requestDto = objectMapper.readValue(request.getRequestBody(), MovieUpdateRequestDto.class);
                        movieService.patch(request.getMovie().getId(), requestDto);

                    } catch (IllegalAccessException | IntrospectionException | InvocationTargetException e) {
                        System.out.println(e);
                    }
                }

                request.setStatus(requestStatus);
            }
            case RequestStatus.REPROVADO -> {
                request.setStatus(requestStatus);
            }
        }

        requestRepository.save(request);

        return RequestMovieResponseDto.toRequestDto(request);
    }

    public void delete(Long id) {
        Request request = requestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Request not found with id: " + id));

        requestRepository.delete(request);
    }

    public void deleteRequestByCurrentUser(Long id) {
        Request request = requestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Request not found with id: " + id));

        User user = authenticatedUserService.getCurrentUser();

        if (user != request.getUser()) {
            throw new ForbiddenOperationException("You don't have permission to delete another user's requests.");
        }
        requestRepository.delete(request);
    }
}
