package com.project.cinescope.actor.impl;

import com.project.cinescope.actor.Actor;
import com.project.cinescope.actor.ActorRepository;
import com.project.cinescope.actor.ActorService;
import com.project.cinescope.actor.response.ActorResponseDto;
import com.project.cinescope.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
