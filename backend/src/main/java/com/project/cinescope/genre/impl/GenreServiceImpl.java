package com.project.cinescope.genre.impl;

import com.project.cinescope.exception.ResourceNotFoundException;
import com.project.cinescope.genre.Genre;
import com.project.cinescope.genre.GenreRepository;
import com.project.cinescope.genre.GenreService;
import com.project.cinescope.genre.response.GenreResponseDto;
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
                .map(GenreResponseDto::toGenreDto)
                .toList();
    }

    public GenreResponseDto getById(Long id) {
        return genreRepository.findById(id)
                .map(GenreResponseDto::toGenreDto)
                .orElseThrow(() -> new ResourceNotFoundException("No genre find with especified id"));
    }
}
