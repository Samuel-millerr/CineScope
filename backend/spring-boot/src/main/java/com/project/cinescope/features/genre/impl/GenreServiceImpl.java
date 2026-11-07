package com.project.cinescope.features.genre.impl;

import com.project.cinescope.features.genre.Genre;
import com.project.cinescope.features.genre.GenreRepository;
import com.project.cinescope.features.genre.GenreService;
import com.project.cinescope.features.genre.request.GenreRequestDto;
import com.project.cinescope.features.genre.response.GenreResponseDto;
import com.project.cinescope.core.exception.DefaultException;
import com.project.cinescope.core.utils.message.ErrorEnum;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public List<GenreResponseDto> getAll() {
        List<Genre> genreList = genreRepository.findAll();
        return genreList.stream()
                .map(GenreResponseDto::toResponseDto)
                .toList();
    }

    @Override
    public GenreResponseDto getById(Integer id) {
        Genre genre = findGenreById(id);
        return GenreResponseDto.toResponseDto(genre);
    }

    @Override
    public GenreResponseDto post(GenreRequestDto requestDto) {
        String genreName = requestDto.genre();
        if (genreRepository.existsByGenre(genreName)) {
            throw new DefaultException(
                    HttpStatus.CONFLICT,
                    ErrorEnum.DUPLICATE.formatDetailedTemplate("Genre", "genre", genreName)
            );
        }

        Genre genre = GenreRequestDto.toEntity(requestDto);
        Genre createdGenre = genreRepository.save(genre);
        return GenreResponseDto.toResponseDto(createdGenre);
    }

    @Override
    public void delete(Integer id) {
        Genre genre = findGenreById(id);

        genre.setActive(false);
        genreRepository.save(genre);
    }

    private Genre findGenreById(Integer id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new DefaultException(
                                HttpStatus.NOT_FOUND,
                                ErrorEnum.NOT_FOUND.formatDetailedTemplate("Genre", "id", id)
                        )
                );
    }
}
