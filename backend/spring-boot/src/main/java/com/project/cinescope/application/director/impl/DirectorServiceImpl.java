package com.project.cinescope.application.director.impl;

import com.project.cinescope.application.director.Director;
import com.project.cinescope.application.director.DirectorRepository;
import com.project.cinescope.application.director.DirectorService;
import com.project.cinescope.application.director.request.DirectorRequestDto;
import com.project.cinescope.application.director.response.DirectorResponseDto;
import com.project.cinescope.core.exception.DefaultException;
import com.project.cinescope.core.exception.exceptions.ResourceNotFoundException;
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
    public DirectorResponseDto getById(Long id) {
        return directorRepository.findById(id)
                .map(DirectorResponseDto::toResponseDto)
                .orElseThrow(() -> new DefaultException(
                                HttpStatus.NOT_FOUND,
                                ErrorEnum.NOT_FOUND.formatDetailedTemplate("Director", "id", id)
                        )
                );
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
    public DirectorResponseDto patch(Long id, DirectorRequestDto requestDto) {
        Director director = directorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Director not found with id: " + id));

        if (requestDto.name() != null) {
            director.setName(requestDto.name());
        }

        Director updateActor = directorRepository.save(director);
        return DirectorResponseDto.toResponseDto(updateActor);
    }

    @Override
    public void delete(Long id) {
        Director director = directorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Director not found with id: " + id));

        directorRepository.delete(director);
    }
}
