package com.project.cinescope.application.actor;

import com.project.cinescope.application.actor.impl.ActorServiceImpl;
import com.project.cinescope.application.actor.request.ActorRequestDto;
import com.project.cinescope.application.actor.response.ActorResponseDto;
import com.project.cinescope.core.exception.exceptions.DuplicateResourceException;
import com.project.cinescope.core.exception.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.tuple;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ActorServiceTest {

    @Mock
    private ActorRepository actorRepository;

    @InjectMocks
    private ActorServiceImpl actorService;

    private Actor actor1;
    private Actor actor2;
    private Actor actor3;

    @BeforeEach
    void setUp() {
        actor1 = new Actor(1L, "Tom Hanks", "photo1.jpg", true, Collections.emptyList());
        actor2 = new Actor(2L, "Meryl Streep", "photo2.jpg", true, Collections.emptyList());
        actor3 = new Actor(3L, "Leonardo DiCaprio", "photo3.jpg", true, Collections.emptyList());
    }

    @Test
    @DisplayName("Should return all actors mapped to response DTOs")
    void shouldReturnMultipleActors() {
        when(actorRepository.findAll()).thenReturn(Arrays.asList(actor1, actor2, actor3));

        List<ActorResponseDto> result = actorService.getAll();

        assertThat(result)
                .hasSize(3)
                .extracting(ActorResponseDto::id, ActorResponseDto::name)
                .containsExactly(
                        tuple(1L, "Tom Hanks"),
                        tuple(2L, "Meryl Streep"),
                        tuple(3L, "Leonardo DiCaprio")
                );
    }

    @Test
    @DisplayName("Should return an actor by ID")
    void shouldReturnActorById() {
        when(actorRepository.findById(1L)).thenReturn(Optional.of(actor1));

        ActorResponseDto result = actorService.getById(1L);

        assertThat(result)
                .isNotNull()
                .extracting(ActorResponseDto::id, ActorResponseDto::name, ActorResponseDto::photo)
                .containsExactly(1L, "Tom Hanks", "photo1.jpg");

        verify(actorRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Should throw ResourceNotFoundException when actor not found by ID")
    void shouldThrowNotFoundExceptionWhenActorDoesNotExist() {
        when(actorRepository.findById(999L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> actorService.getById(999L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Actor not found with id: 999");

        verify(actorRepository, times(1)).findById(999L);
    }

    @Test
    @DisplayName("Should create a new actor")
    void shouldCreateNewActor() {
        ActorRequestDto requestDto = new ActorRequestDto("Tom Cruise", "photo_cruise.jpg");
        Actor newActor = new Actor(4L, "Tom Cruise", "photo_cruise.jpg", true, Collections.emptyList());

        when(actorRepository.existsByName("Tom Cruise")).thenReturn(false);
        when(actorRepository.save(any(Actor.class))).thenReturn(newActor);

        ActorResponseDto result = actorService.post(requestDto);

        assertThat(result)
                .isNotNull()
                .extracting(ActorResponseDto::id, ActorResponseDto::name, ActorResponseDto::photo)
                .containsExactly(4L, "Tom Cruise", "photo_cruise.jpg");

        verify(actorRepository, times(1)).existsByName("Tom Cruise");
        verify(actorRepository, times(1)).save(any(Actor.class));
    }

    @Test
    @DisplayName("Should throw DuplicateResourceException when actor name already exists")
    void shouldThrowDuplicateExceptionWhenActorAlreadyExists() {
        ActorRequestDto requestDto = new ActorRequestDto("Tom Hanks", "photo.jpg");

        when(actorRepository.existsByName("Tom Hanks")).thenReturn(true);

        assertThatThrownBy(() -> actorService.post(requestDto))
                .isInstanceOf(DuplicateResourceException.class)
                .hasMessage("Actor with name Tom Hanks already exists");

        verify(actorRepository, times(1)).existsByName("Tom Hanks");
        verify(actorRepository, never()).save(any(Actor.class));
    }

    @Test
    @DisplayName("Should update an existing actor")
    void shouldUpdateExistingActor() {
        ActorRequestDto updateDto = new ActorRequestDto("Tom Hanks Updated", "updated_photo.jpg");
        Actor existingActor = new Actor(1L, "Tom Hanks", "photo1.jpg", true, Collections.emptyList());
        Actor updatedActor = new Actor(1L, "Tom Hanks Updated", "updated_photo.jpg", true, Collections.emptyList());

        when(actorRepository.findById(1L)).thenReturn(Optional.of(existingActor));
        when(actorRepository.save(any(Actor.class))).thenReturn(updatedActor);

        ActorResponseDto result = actorService.patch(1L, updateDto);

        assertThat(result)
                .isNotNull()
                .extracting(ActorResponseDto::id, ActorResponseDto::name, ActorResponseDto::photo)
                .containsExactly(1L, "Tom Hanks Updated", "updated_photo.jpg");

        verify(actorRepository, times(1)).findById(1L);
        verify(actorRepository, times(1)).save(any(Actor.class));
    }

    @Test
    @DisplayName("Should throw ResourceNotFoundException when updating non-existent actor")
    void shouldThrowNotFoundWhenPatchingNonExistentActor() {
        ActorRequestDto updateDto = new ActorRequestDto("Updated Name", "updated_photo.jpg");

        when(actorRepository.findById(999L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> actorService.patch(999L, updateDto))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Actor not found with id: 999");

        verify(actorRepository, times(1)).findById(999L);
        verify(actorRepository, never()).save(any(Actor.class));
    }

    @Test
    @DisplayName("Should delete an actor")
    void shouldDeleteActor() {
        when(actorRepository.findById(1L)).thenReturn(Optional.of(actor1));

        actorService.delete(1L);

        verify(actorRepository, times(1)).findById(1L);
        verify(actorRepository, times(1)).delete(actor1);
    }

    @Test
    @DisplayName("Should throw ResourceNotFoundException when deleting non-existent actor")
    void shouldThrowNotFoundWhenDeletingNonExistentActor() {
        when(actorRepository.findById(999L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> actorService.delete(999L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Actor not found with id: 999");

        verify(actorRepository, times(1)).findById(999L);
        verify(actorRepository, never()).delete(any(Actor.class));
    }
}