package com.project.cinescope.features.director;

import com.project.cinescope.features.director.request.DirectorRequestDTO;
import com.project.cinescope.features.director.response.DirectorResponseDTO;

import java.util.List;

public interface DirectorService {
    List<DirectorResponseDTO> getAll();

    DirectorResponseDTO getById(Integer id);

    DirectorResponseDTO post(DirectorRequestDTO request);

    DirectorResponseDTO patch(Integer id, DirectorRequestDTO request);

    void delete(Integer id);
}
