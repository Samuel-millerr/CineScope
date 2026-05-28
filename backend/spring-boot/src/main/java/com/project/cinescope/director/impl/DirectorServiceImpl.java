package com.project.cinescope.director.impl;

import com.project.cinescope.director.Director;
import com.project.cinescope.director.DirectorRepository;
import com.project.cinescope.director.DirectorService;
import com.project.cinescope.director.request.DirectorRequestDto;
import com.project.cinescope.director.response.DirectorResponseDto;
import com.project.cinescope.shared.exception.exceptions.ResourceNotFoundException;
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

    public void delete(Long id) {
        Optional<Director> director = directorRepository.findById(id);
        if (director.isEmpty()) {
            throw  new ResourceNotFoundException("Director not found with id: " + id);
        }

        directorRepository.delete(director.get());
    }
}
