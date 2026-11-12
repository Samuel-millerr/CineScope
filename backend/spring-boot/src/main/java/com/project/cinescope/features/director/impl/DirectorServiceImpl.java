package com.project.cinescope.features.director.impl;

import com.project.cinescope.features.director.Director;
import com.project.cinescope.features.director.DirectorMapper;
import com.project.cinescope.features.director.DirectorRepository;
import com.project.cinescope.features.director.DirectorService;
import com.project.cinescope.features.director.request.DirectorRequestDTO;
import com.project.cinescope.features.director.response.DirectorResponseDTO;
import com.project.cinescope.core.exception.DefaultException;
import com.project.cinescope.core.utils.message.ErrorEnum;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirectorServiceImpl implements DirectorService {

    private final DirectorRepository directorRepository;
    private final DirectorMapper directorMapper;

    public DirectorServiceImpl(DirectorRepository directorRepository, DirectorMapper directorMapper) {
        this.directorRepository = directorRepository;
        this.directorMapper = directorMapper;
    }

    @Override
    public List<DirectorResponseDTO> getAll() {
        List<Director> directorList = directorRepository.findAll();
        return directorList.stream()
                .map(directorMapper::toResponse)
                .toList();
    }

    @Override
    public DirectorResponseDTO getById(Integer id) {
        Director director = findDirectorById(id);
        return directorMapper.toResponse(director);
    }

    @Override
    public DirectorResponseDTO post(DirectorRequestDTO request) {
        String directorName = request.name();

        if (directorRepository.existsByName(directorName)) {
            throw new DefaultException(
                    HttpStatus.CONFLICT,
                    ErrorEnum.DUPLICATE.formatDetailedTemplate("Director", "name", directorName)
            );
        }

        Director director = directorMapper.toEntity(request);
        Director createdDirector = directorRepository.save(director);
        return directorMapper.toResponse(createdDirector);
    }

    @Override
    public DirectorResponseDTO patch(Integer id, DirectorRequestDTO request) {
        Director director = findDirectorById(id);

        directorMapper.updateEntity(request, director);

        Director updateActor = directorRepository.save(director);
        return directorMapper.toResponse(updateActor);
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
