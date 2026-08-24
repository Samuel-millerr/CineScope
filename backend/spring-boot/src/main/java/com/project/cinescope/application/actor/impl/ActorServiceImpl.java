package com.project.cinescope.application.actor.impl;

import com.project.cinescope.application.actor.Actor;
import com.project.cinescope.application.actor.ActorRepository;
import com.project.cinescope.application.actor.ActorService;
import com.project.cinescope.application.actor.request.ActorRequestDto;
import com.project.cinescope.application.actor.response.ActorResponseDto;
import com.project.cinescope.core.exception.DefaultException;
import com.project.cinescope.core.utils.message.ErrorEnum;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorServiceImpl implements ActorService {
    private final ActorRepository actorRepository;

    public ActorServiceImpl(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    @Override
    public List<ActorResponseDto> getAll() {
        List<Actor> actorList = actorRepository.findAll();
        return actorList.stream()
                .map(ActorResponseDto::toResponseDto)
                .toList();
    }

    @Override
    public ActorResponseDto getById(Integer id) {
        Actor actor = getActorById(id);
        return ActorResponseDto.toResponseDto(actor);
    }

    @Override
    public ActorResponseDto post(ActorRequestDto requestDto) {
        String actorName = requestDto.name();
        if (actorRepository.existsByName(actorName)) {
            throw new DefaultException(
                    HttpStatus.CONFLICT,
                    ErrorEnum.DUPLICATE.formatDetailedTemplate("Actor", "name", actorName)
            );
        }

        Actor actor = ActorRequestDto.toEntity(requestDto);
        Actor createdActor = actorRepository.save(actor);
        return ActorResponseDto.toResponseDto(createdActor);
    }

    @Override
    public ActorResponseDto patch(Integer id, ActorRequestDto requestDto) {
        Actor actor = getActorById(id);

        if (requestDto.name() != null) {
            actor.setName(requestDto.name());
        }

        if (requestDto.photo() != null) {
            actor.setPhoto(requestDto.photo());
        }

        Actor updateActor = actorRepository.save(actor);
        return ActorResponseDto.toResponseDto(updateActor);
    }

    @Override
    public void delete(Integer id) {
        Actor actor = getActorById(id);

        actor.setActive(false);
        actorRepository.save(actor);
    }

    private Actor getActorById(Integer id) {
        return actorRepository.findById(id)
                .orElseThrow(() -> new DefaultException(
                                HttpStatus.NOT_FOUND,
                                ErrorEnum.NOT_FOUND.formatDetailedTemplate("Actor", "id", id)
                        )
                );
    }
}
