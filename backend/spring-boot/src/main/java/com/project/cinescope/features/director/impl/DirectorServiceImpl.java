package com.project.cinescope.features.director.impl;

import com.project.cinescope.features.director.Director;
import com.project.cinescope.features.director.DirectorRepository;
import com.project.cinescope.features.director.DirectorService;
import com.project.cinescope.features.director.request.DirectorRequestDto;
import com.project.cinescope.features.director.response.DirectorResponseDto;
import com.project.cinescope.core.exception.DefaultException;
import com.project.cinescope.core.utils.message.ErrorEnum;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirectorServiceImpl implements DirectorService {
    private final DirectorRepository directorRepository;

    public DirectorServiceImpl(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }

    @Override
    public List<DirectorResponseDto> getAll() {
        List<Director> directorList = directorRepository.findAll();
        return directorList.stream()
                .map(DirectorResponseDto::toResponseDto)
                .toList();
    }

    @Override
    public DirectorResponseDto getById(Integer id) {
        Director director = findDirectorById(id);
        return DirectorResponseDto.toResponseDto(director);
    }

    @Override
    public DirectorResponseDto post(DirectorRequestDto requestDto) {
        String directorName = requestDto.name();

        if (directorRepository.existsByName(directorName)) {
            throw new DefaultException(
                    HttpStatus.CONFLICT,
                    ErrorEnum.DUPLICATE.formatDetailedTemplate("Director", "name", directorName)
            );
        }

        Director director = DirectorRequestDto.toEntity(requestDto);
        Director createdDirector = directorRepository.save(director);
        return DirectorResponseDto.toResponseDto(createdDirector);
    }

    @Override
    public DirectorResponseDto patch(Integer id, DirectorRequestDto requestDto) {
        Director director = findDirectorById(id);

        if (requestDto.name() != null) {
            director.setName(requestDto.name());
        }

        Director updateActor = directorRepository.save(director);
        return DirectorResponseDto.toResponseDto(updateActor);
    }

    @Override
    public void delete(Integer id) {
        Director director = findDirectorById(id);

        director.setActive(false);
        directorRepository.save(director);
    }

    private Director findDirectorById(Integer id) {
        return directorRepository.findById(id)
                .orElseThrow(() -> new DefaultException(
                                HttpStatus.NOT_FOUND,
                                ErrorEnum.NOT_FOUND.formatDetailedTemplate("Director", "id", id)
                        )
                );
    }
}
