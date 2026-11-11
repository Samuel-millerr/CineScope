package com.project.cinescope.features.actor;

import com.project.cinescope.core.config.MapperConfiguration;
import com.project.cinescope.features.actor.request.ActorRequestDTO;
import com.project.cinescope.features.actor.response.ActorResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(config = MapperConfiguration.class)
public interface ActorMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "movies", ignore = true)
    Actor toEntity(ActorRequestDTO request);

    ActorResponseDTO toResponse(Actor actor);

    List<ActorResponseDTO> toResponseList(List<Actor> actors);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "movies", ignore = true)
    void updateEntity(ActorRequestDTO requestDTO, @MappingTarget Actor actor);

}
