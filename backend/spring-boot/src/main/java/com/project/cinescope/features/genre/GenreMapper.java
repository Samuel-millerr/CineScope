package com.project.cinescope.features.genre;

import com.project.cinescope.features.genre.request.GenreRequestDTO;
import com.project.cinescope.features.genre.response.GenreResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GenreMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "movies", ignore = true)
    Genre toEntity(GenreRequestDTO request);

    GenreResponseDTO toResponse(Genre genre);

    List<GenreResponseDTO> toResponseList(List<Genre> genres);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "movies", ignore = true)
    void updateEntity(GenreRequestDTO request, @MappingTarget Genre genre);

}

