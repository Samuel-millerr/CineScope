package com.project.cinescope.features.genre.impl;

import com.project.cinescope.features.genre.Genre;
import com.project.cinescope.features.genre.GenreMapper;
import com.project.cinescope.features.genre.GenreRepository;
import com.project.cinescope.features.genre.GenreService;
import com.project.cinescope.features.genre.request.GenreRequestDTO;
import com.project.cinescope.features.genre.response.GenreResponseDTO;
import com.project.cinescope.core.exception.DefaultException;
import com.project.cinescope.core.utils.message.ErrorEnum;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    public GenreServiceImpl(GenreRepository genreRepository, GenreMapper genreMapper) {
        this.genreRepository = genreRepository;
        this.genreMapper = genreMapper;
    }

    @Override
    public List<GenreResponseDTO> getAll() {
        List<Genre> genreList = genreRepository.findAll();
        return genreList.stream()
                .map(genreMapper::toResponse)
                .toList();
    }

    @Override
    public GenreResponseDTO getById(Integer id) {
        Genre genre = findGenreById(id);
        return genreMapper.toResponse(genre);
    }

    @Override
    public GenreResponseDTO post(GenreRequestDTO request) {
        String genreName = request.genre();

        if (genreRepository.existsByGenre(genreName)) {
            throw new DefaultException(
                    HttpStatus.CONFLICT,
                    ErrorEnum.DUPLICATE.formatDetailedTemplate("Genre", "genre", genreName)
            );
        }

        Genre genre = genreMapper.toEntity(request);
        Genre createdGenre = genreRepository.save(genre);
        return genreMapper.toResponse(createdGenre);
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
