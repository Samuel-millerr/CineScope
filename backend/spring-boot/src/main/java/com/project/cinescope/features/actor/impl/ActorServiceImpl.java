package com.project.cinescope.features.actor.impl;

import com.project.cinescope.core.exception.DefaultException;
import com.project.cinescope.core.utils.message.ErrorEnum;
import com.project.cinescope.features.actor.Actor;
import com.project.cinescope.features.actor.ActorMapper;
import com.project.cinescope.features.actor.ActorRepository;
import com.project.cinescope.features.actor.ActorService;
import com.project.cinescope.features.actor.request.ActorRequestDTO;
import com.project.cinescope.features.actor.response.ActorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorServiceImpl implements ActorService {

    private final ActorRepository actorRepository;
    private final ActorMapper actorMapper;

    public ActorServiceImpl(ActorRepository actorRepository, ActorMapper actorMapper) {
        this.actorRepository = actorRepository;
        this.actorMapper = actorMapper;
    }

    @Override
    public List<ActorResponseDTO> getAll() {
        List<Actor> actors = actorRepository.findAll();
        return actors.stream()
                .map(actorMapper::toResponse)
                .toList();
    }

    @Override
    public ActorResponseDTO getById(Integer id) {
        Actor actor = findActorById(id);
        return actorMapper.toResponse(actor);
    }

    @Override
    public ActorResponseDTO post(ActorRequestDTO request) {
        String actorName = request.name();
        if (actorRepository.existsByName(actorName)) {
            throw new DefaultException(
                    HttpStatus.CONFLICT,
                    ErrorEnum.DUPLICATE.formatDetailedTemplate("Actor", "name", actorName)
            );
        }

        Actor actor = actorMapper.toEntity(request);
        Actor createdActor = actorRepository.save(actor);
        return actorMapper.toResponse(createdActor);
    }

    @Override
    public ActorResponseDTO patch(Integer id, ActorRequestDTO request) {
        Actor actor = findActorById(id);

        actorMapper.updateEntity(request, actor);

        Actor updateActor = actorRepository.save(actor);
        return actorMapper.toResponse(updateActor);
    }

    @Override
    public void delete(Integer id) {
        Actor actor = findActorById(id);

        actor.setActive(false);
        actorRepository.save(actor);
    }

    private Actor findActorById(Integer id) {
        return actorRepository.findById(id)
                .orElseThrow(() -> new DefaultException(
                                HttpStatus.NOT_FOUND,
                                ErrorEnum.NOT_FOUND.formatDetailedTemplate("Actor", "id", id)
                        )
                );
    }

}
