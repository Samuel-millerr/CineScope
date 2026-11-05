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
import com.project.cinescope.core.exception.DefaultException;
import com.project.cinescope.core.utils.message.ErrorEnum;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Set;

@Service
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;
    private final MovieService movieService;
    private final AuthenticatedUserService authenticatedUserService;
    private final ObjectMapper objectMapper;
    private final Validator validator;

    public RequestServiceImpl(RequestRepository requestRepository,
                              UserRepository userRepository,
                              MovieRepository movieRepository,
                              MovieService movieService,
                              AuthenticatedUserService authenticatedUserService,
                              ObjectMapper objectMapper,
                              Validator validator) {
        this.requestRepository = requestRepository;
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
        this.movieService = movieService;
        this.authenticatedUserService = authenticatedUserService;
        this.objectMapper = objectMapper;
        this.validator = validator;
    }

    @Override
    public List<RequestMovieResponseDto> getAll() {
        List<Request> requestList = requestRepository.findAll();
        return requestList.stream()
                .map(RequestMovieResponseDto::toResponseDto)
                .toList();
    }

    @Override
    public RequestMovieResponseDto getById(Integer id) {
        Request request = findRequestById(id);
        return RequestMovieResponseDto.toResponseDto(request);
    }

    @Override
    public List<RequestMovieResponseDto> findRequestsByCurrentUser() {
        User user = authenticatedUserService.getCurrentUser();
        List<Request> requestList = requestRepository.findByUser(user);
        return requestList.stream()
                .map(RequestMovieResponseDto::toResponseDto)
                .toList();
    }

    @Override
    public List<RequestMovieResponseDto> findRequestsByUserId(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new DefaultException(
                                HttpStatus.NOT_FOUND,
                                ErrorEnum.NOT_FOUND.formatDetailedTemplate("User", "id", userId)
                        )
                );

        List<Request> requestList = requestRepository.findByUser(user);
        return requestList.stream()
                .map(RequestMovieResponseDto::toResponseDto)
                .toList();
    }

    @Override
    public RequestMovieResponseDto post(RequestMovieRequestDto requestDto) {
        User user = authenticatedUserService.getCurrentUser();

        Request request = RequestMovieRequestDto.toEntity(requestDto);
        request.setUser(user);

        Object requestBody;
        switch (requestDto.requestType()) {
            case ADIÇÃO -> {
                MovieCreateRequestDto movieCreateRequestDto = objectMapper.treeToValue(requestDto.requestBody(), MovieCreateRequestDto.class);

                Set<ConstraintViolation<MovieCreateRequestDto>> violations = validator.validate(movieCreateRequestDto);

                if (!violations.isEmpty()) {
                    throw new DefaultException(
                            HttpStatus.CONFLICT,
                            "Validation failed for request body: " + violations
                    );
                }

                requestBody = objectMapper.treeToValue(requestDto.requestBody(), MovieCreateRequestDto.class);
            }
            case EDIÇÃO -> {
                Movie movie = movieRepository.findById(requestDto.movieId())
                        .orElseThrow(() -> new DefaultException(
                                        HttpStatus.NOT_FOUND,
                                        ErrorEnum.NOT_FOUND.formatDetailedTemplate("Movie", "id", requestDto.movieId())
                                )
                        );

                request.setMovie(movie);

                requestBody = objectMapper.treeToValue(requestDto.requestBody(), MovieUpdateRequestDto.class);
            }
            default -> throw new IllegalArgumentException("Invalid request type");
        }

        request.setRequestBody(objectMapper.writeValueAsString(requestBody));

        Request createdRequest = requestRepository.save(request);
        return RequestMovieResponseDto.toResponseDto(createdRequest);
    }

    @Override
    public RequestMovieResponseDto updateStatus(
            Integer id,
            RequestStatus requestStatus
    ) {

        Request request = findRequestById(id);

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

        return RequestMovieResponseDto.toResponseDto(request);
    }

    @Override
    public void delete(Integer id) {
        Request request = findRequestById(id);

        request.setActive(false);
        requestRepository.save(request);
    }

    @Override
    public void deleteRequestByCurrentUser(Integer id) {
        Request request = findRequestById(id);

        User user = authenticatedUserService.getCurrentUser();

        if (user != request.getUser()) {
            throw new DefaultException(
                    HttpStatus.FORBIDDEN,
                    "You don't have permission to delete another user's requests."
            );
        }

        request.setActive(false);
        requestRepository.save(request);
    }

    private Request findRequestById(Integer id) {
        return requestRepository.findById(id)
                .orElseThrow(() -> new DefaultException(
                                HttpStatus.NOT_FOUND,
                                ErrorEnum.NOT_FOUND.formatDetailedTemplate("Request", "id", id)
                        )
                );
    }
}
