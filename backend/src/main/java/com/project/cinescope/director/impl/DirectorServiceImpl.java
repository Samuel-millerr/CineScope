package com.project.cinescope.director.impl;

import com.project.cinescope.director.Director;
import com.project.cinescope.director.DirectorRepository;
import com.project.cinescope.director.DirectorService;
import com.project.cinescope.director.response.DirectorResponseDto;
import com.project.cinescope.exception.ResourceNotFoundException;
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
        return directorList
                .stream().
                map(DirectorResponseDto::toDirectorDto)
                .toList();
    }

    public DirectorResponseDto getById(Long id) {
        return directorRepository.findById(id)
                .map(DirectorResponseDto::toDirectorDto)
                .orElseThrow(() -> new ResourceNotFoundException("No director find with especified id"));
    }
}
