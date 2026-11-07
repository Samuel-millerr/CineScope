package com.project.cinescope.features.movie.impl;

import com.project.cinescope.features.movie.Movie;
import com.project.cinescope.features.movie.MovieRepository;
import com.project.cinescope.features.movie.MovieService;
import com.project.cinescope.features.movie.request.MovieCreateRequestDto;
import com.project.cinescope.features.movie.request.MovieUpdateRequestDto;
import com.project.cinescope.features.movie.response.MovieResponseDto;
import com.project.cinescope.core.exception.DefaultException;
import com.project.cinescope.core.utils.message.ErrorEnum;
import org.springframework.http.HttpStatus;
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

    @Override
    public List<MovieResponseDto> getAll() {
        List<Movie> movieList = movieRepository.findAll();
        return movieList.stream()
                .map(MovieResponseDto::toResponseDto)
                .toList();
    }

    @Override
    public MovieResponseDto getById(Integer id) {
        Movie movie = findMovieById(id);
        return MovieResponseDto.toResponseDto(movie);
    }

    @Override
    public MovieResponseDto post(MovieCreateRequestDto requestDto) {
        String movieTitle = requestDto.title();

        if (movieRepository.existsByTitle(movieTitle)) {
            throw new DefaultException(
                    HttpStatus.CONFLICT,
                    ErrorEnum.DUPLICATE.formatDetailedTemplate("Movie", "title", movieTitle)
            );
        }

        Movie movie = MovieCreateRequestDto.toEntity(requestDto);
        Movie createdMovie = movieRepository.save(movie);

        return MovieResponseDto.toResponseDto(createdMovie);
    }

    @Override
    public MovieResponseDto patch(Integer id, MovieUpdateRequestDto requestDto) {
        Movie movie = findMovieById(id);

        try {
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

        return MovieResponseDto.toResponseDto(updatedMovie);
    }

    @Override
    public void delete(Integer id) {
        Movie movie = findMovieById(id);

        movie.setActive(false);
        movieRepository.save(movie);
    }

    private Movie findMovieById(Integer id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new DefaultException(
                                HttpStatus.NOT_FOUND,
                                ErrorEnum.NOT_FOUND.formatDetailedTemplate("Movie", "id", id)
                        )
                );


    }
}
