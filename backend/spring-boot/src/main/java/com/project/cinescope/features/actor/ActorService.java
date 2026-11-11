package com.project.cinescope.features.actor;

import com.project.cinescope.features.actor.request.ActorRequestDTO;
import com.project.cinescope.features.actor.response.ActorResponseDTO;

import java.util.List;

public interface ActorService {
    List<ActorResponseDTO> getAll();

    ActorResponseDTO getById(Integer id);

    ActorResponseDTO post(ActorRequestDTO request);

    ActorResponseDTO patch(Integer id, ActorRequestDTO request);

    void delete(Integer id);
}
