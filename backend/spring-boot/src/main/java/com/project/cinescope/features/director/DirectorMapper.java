package com.project.cinescope.features.director;

import com.project.cinescope.features.director.request.DirectorRequestDTO;
import com.project.cinescope.features.director.response.DirectorResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DirectorMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "movies", ignore = true)
    Director toEntity(DirectorRequestDTO request);

    DirectorResponseDTO toResponse(Director director);

    List<DirectorResponseDTO> toResponseList(List<Director> directors);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "movies", ignore = true)
    void updateEntity(DirectorRequestDTO requestDTO, @MappingTarget Director director);

}

