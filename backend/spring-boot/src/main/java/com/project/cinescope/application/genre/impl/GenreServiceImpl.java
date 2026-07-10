package com.project.cinescope.application.genre.impl;

import com.project.cinescope.application.genre.Genre;
import com.project.cinescope.application.genre.GenreRepository;
import com.project.cinescope.application.genre.GenreService;
import com.project.cinescope.application.genre.request.GenreRequestDto;
import com.project.cinescope.application.genre.response.GenreResponseDto;
import com.project.cinescope.core.exception.exceptions.DuplicateResourceException;
import com.project.cinescope.core.exception.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<GenreResponseDto> getAll() {
        List<Genre> genreList = genreRepository.findAll();
        return genreList.stream()
                .map(GenreResponseDto::toResponseDto)
                .toList();
    }

    public GenreResponseDto getById(Long id) {
        return genreRepository.findById(id)
                .map(GenreResponseDto::toResponseDto)
                .orElseThrow(() -> new ResourceNotFoundException("Genre not found with id: " + id));
    }

    public GenreResponseDto post(GenreRequestDto requestDto) {
        String genreName = requestDto.genre();
        if (genreRepository.existsByGenre(genreName)) {
            throw new DuplicateResourceException("This genre already exists");
        }

        Genre genre = GenreRequestDto.toEntity(requestDto);
        Genre createdGenre = genreRepository.save(genre);
        return GenreResponseDto.toResponseDto(createdGenre);
    }

    public void delete(Long id) {
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Genre not found with id: " + id));

        genreRepository.delete(genre);
    }
}
