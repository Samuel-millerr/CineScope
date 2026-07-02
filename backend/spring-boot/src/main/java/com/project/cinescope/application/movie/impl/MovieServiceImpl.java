package com.project.cinescope.application.movie.impl;

import com.project.cinescope.application.movie.Movie;
import com.project.cinescope.application.movie.MovieRepository;
import com.project.cinescope.application.movie.MovieService;
import com.project.cinescope.application.movie.request.MovieRequestDto;
import com.project.cinescope.application.movie.response.MovieResponseDto;
import com.project.cinescope.core.exception.exceptions.DuplicateResourceException;
import com.project.cinescope.core.exception.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<MovieResponseDto> getAll() {
        List<Movie> movieList = movieRepository.findAll();
        return movieList.stream()
                .map(MovieResponseDto::toMovieDto)
                .toList();
    }

    public MovieResponseDto getById(Long id) {
        return movieRepository.findById(id)
                .map(MovieResponseDto::toMovieDto)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id " + id));
    }

    public MovieResponseDto post(MovieRequestDto requestDto) {
        String movieTitle = requestDto.title();
        if (movieRepository.existsByTitle(movieTitle)) {
            throw new DuplicateResourceException("Movie with title " + movieTitle + " already exists");
        }

        Movie movie = MovieRequestDto.toMovie(requestDto);
        Movie createdMovie = movieRepository.save(movie);
        return MovieResponseDto.toMovieDto(createdMovie);
    }

    public MovieResponseDto patch(Long id, MovieRequestDto requestDto) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: " + id));

        try {
            int count = 0;
            Field[] fields = requestDto.getClass().getDeclaredFields();
            PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(movie.getClass()).getPropertyDescriptors();
            for (Field field : fields) {
                field.setAccessible(true);

                if (field.get(requestDto) != null) {
                    for (PropertyDescriptor pd : propertyDescriptors) {
                        if (field.getName().equals(pd.getName())) {
                            Method setter = pd.getWriteMethod();
                            setter.invoke(movie, field.get(requestDto));
                        }
                    }
                }
            }
        } catch (IllegalAccessException | IntrospectionException | InvocationTargetException e) {
            System.out.println(e);
        }
        Movie updatedMovie = movieRepository.save(movie);
        return MovieResponseDto.toMovieDto(updatedMovie);
    }

    public void delete(Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: " + id));

        movieRepository.delete(movie);
    }
}
