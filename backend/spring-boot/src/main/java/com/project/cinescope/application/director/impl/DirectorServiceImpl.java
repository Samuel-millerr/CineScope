package com.project.cinescope.application.director.impl;

import com.project.cinescope.application.director.Director;
import com.project.cinescope.application.director.DirectorRepository;
import com.project.cinescope.application.director.DirectorService;
import com.project.cinescope.application.director.request.DirectorRequestDto;
import com.project.cinescope.application.director.response.DirectorResponseDto;
import com.project.cinescope.core.exception.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DirectorServiceImpl implements DirectorService {
    private final DirectorRepository directorRepository;

    public DirectorServiceImpl(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }

    public List<DirectorResponseDto> getAll() {
        List<Director> directorList = directorRepository.findAll();
        return directorList.stream()
                .map(DirectorResponseDto::toDirectorDto)
                .toList();
    }

    public DirectorResponseDto getById(Long id) {
        return directorRepository.findById(id)
                .map(DirectorResponseDto::toDirectorDto)
                .orElseThrow(() -> new ResourceNotFoundException("Director not found with id: " + id));
    }

    public DirectorResponseDto post(DirectorRequestDto requestDto) {
        String directorName = requestDto.name();
        if (directorRepository.existsByName(directorName)) {
            throw new ResourceNotFoundException("Director with name " + directorName + " already exists");
        }

        Director director = DirectorRequestDto.toDirector(requestDto);
        Director createdDirector = directorRepository.save(director);
        return DirectorResponseDto.toDirectorDto(createdDirector);
    }

    public DirectorResponseDto patch(Long id, DirectorRequestDto requestDto) {
        Director director = directorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Director not found with id: " + id));

        if (requestDto.name() != null) {
            director.setName(requestDto.name());
        }

        Director updateActor = directorRepository.save(director);
        return DirectorResponseDto.toDirectorDto(updateActor);
    }

    public void delete(Long id) {
        Director director = directorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Director not found with id: " + id));

        directorRepository.delete(director);
    }
}
