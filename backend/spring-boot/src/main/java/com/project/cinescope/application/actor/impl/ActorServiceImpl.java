package com.project.cinescope.application.actor.impl;

import com.project.cinescope.application.actor.Actor;
import com.project.cinescope.application.actor.ActorRepository;
import com.project.cinescope.application.actor.ActorService;
import com.project.cinescope.application.actor.request.ActorRequestDto;
import com.project.cinescope.application.actor.response.ActorResponseDto;
import com.project.cinescope.core.exception.exceptions.DuplicateResourceException;
import com.project.cinescope.core.exception.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActorServiceImpl implements ActorService {
    private final ActorRepository actorRepository;

    public ActorServiceImpl(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public List<ActorResponseDto> getAll() {
        List<Actor> actorList = actorRepository.findAll();
        return actorList.stream()
                .map(ActorResponseDto::toActorDto)
                .toList();
    }

    public ActorResponseDto getById(Long id) {
        return actorRepository.findById(id)
                .map(ActorResponseDto::toActorDto)
                .orElseThrow(() -> new ResourceNotFoundException("Actor not found with id: " + id));
    }

    public ActorResponseDto post(ActorRequestDto requestDto) {
        String actorName = requestDto.name();
        if (actorRepository.existsByName(actorName)) {
            throw new DuplicateResourceException("Actor with name " + actorName + " already exists");
        }

        Actor actor = ActorRequestDto.toActor(requestDto);
        Actor createdActor = actorRepository.save(actor);
        return ActorResponseDto.toActorDto(createdActor);
    }

    public ActorResponseDto patch(Long id, ActorRequestDto requestDto) {
        Actor actor = actorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Actor not found with id: " + id));

        if (requestDto.name() != null) {
            actor.setName(requestDto.name());
        }

        if (requestDto.photo() != null) {
            actor.setPhoto(requestDto.photo());
        }

        Actor updateActor = actorRepository.save(actor);
        return ActorResponseDto.toActorDto(updateActor);
    }

    public void delete(Long id) {
        Actor actor = actorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Actor not found with id: " + id));

        actorRepository.delete(actor);
    }
}
