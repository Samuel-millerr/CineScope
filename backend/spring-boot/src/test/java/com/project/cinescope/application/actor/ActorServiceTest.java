package com.project.cinescope.application.actor;

import com.project.cinescope.application.actor.impl.ActorServiceImpl;
import com.project.cinescope.application.actor.request.ActorRequestDto;
import com.project.cinescope.application.actor.response.ActorResponseDto;
import com.project.cinescope.core.exception.DefaultException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
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
        actor1 = new Actor(1, "Tom Hanks", "photo1.jpg", true, null);
        actor2 = new Actor(2, "Meryl Streep", "photo2.jpg", true, null);
        actor3 = new Actor(3, "Leonardo DiCaprio", "photo3.jpg", true, null);
    }

    @Test
    @DisplayName("Should return all actors")
    void shouldReturnAllActors() {
        when(actorRepository.findAll()).thenReturn(Arrays.asList(actor1, actor2, actor3));

        List<ActorResponseDto> result = actorService.getAll();

        assertThat(result)
                .hasSize(3)
                .extracting(ActorResponseDto::id, ActorResponseDto::name)
                .containsExactly(
                        tuple(1, "Tom Hanks"),
                        tuple(2, "Meryl Streep"),
                        tuple(3, "Leonardo DiCaprio")
                );
    }

    @Test
    @DisplayName("Should return an actor by ID")
    void shouldReturnActorById() {
        when(actorRepository.findById(1)).thenReturn(Optional.of(actor1));

        ActorResponseDto result = actorService.getById(1);

        assertThat(result)
                .isNotNull()
                .extracting(ActorResponseDto::id, ActorResponseDto::name, ActorResponseDto::photo)
                .containsExactly(1, "Tom Hanks", "photo1.jpg");

        verify(actorRepository, times(1)).findById(1);
    }

    @Test
    @DisplayName("Should throw NOT_FOUND exception when try get actor by ID that not exists")
    void shouldThrowNotFoundExceptionWhenTryGetActorByIdThatNotExists() {
        when(actorRepository.findById(999)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> actorService.getById(999))
                .isInstanceOf(DefaultException.class)
                .hasMessage("Actor not found with id: 999");

        verify(actorRepository, times(1)).findById(999);
    }

    @Test
    @DisplayName("Should create a new actor")
    void shouldCreateNewActor() {
        ActorRequestDto requestDto = new ActorRequestDto("Tom Cruise", "photo_cruise.jpg");
        Actor newActor = new Actor(4, "Tom Cruise", "photo_cruise.jpg", true, null);

        when(actorRepository.existsByName("Tom Cruise")).thenReturn(false);
        when(actorRepository.save(any(Actor.class))).thenReturn(newActor);

        ActorResponseDto result = actorService.post(requestDto);

        assertThat(result)
                .isNotNull()
                .extracting(ActorResponseDto::id, ActorResponseDto::name, ActorResponseDto::photo)
                .containsExactly(4, "Tom Cruise", "photo_cruise.jpg");

        verify(actorRepository, times(1)).existsByName("Tom Cruise");
        verify(actorRepository, times(1)).save(any(Actor.class));
    }

    @Test
    @DisplayName("Should throw CONFLICT exception when try save actor with actor name already exists")
    void shouldThrowConflictExceptionWhenTrySaveActorWithActorNameAlreadyExists() {
        ActorRequestDto requestDto = new ActorRequestDto("Tom Hanks", "photo.jpg");

        when(actorRepository.existsByName("Tom Hanks")).thenReturn(true);

        assertThatThrownBy(() -> actorService.post(requestDto))
                .isInstanceOf(DefaultException.class)
                .hasMessage("Actor already exists with name: Tom Hanks");

        verify(actorRepository, times(1)).existsByName("Tom Hanks");
        verify(actorRepository, never()).save(any(Actor.class));
    }

    @Test
    @DisplayName("Should update an existing actor")
    void shouldUpdateExistingActor() {
        ActorRequestDto updateDto = new ActorRequestDto("Tom Hanks Updated", "updated_photo.jpg");
        Actor existingActor = new Actor(1, "Tom Hanks", "photo1.jpg", true, null);
        Actor updatedActor = new Actor(1, "Tom Hanks Updated", "updated_photo.jpg", true, null);

        when(actorRepository.findById(1)).thenReturn(Optional.of(existingActor));
        when(actorRepository.save(any(Actor.class))).thenReturn(updatedActor);

        ActorResponseDto result = actorService.patch(1, updateDto);

        assertThat(result)
                .isNotNull()
                .extracting(ActorResponseDto::id, ActorResponseDto::name, ActorResponseDto::photo)
                .containsExactly(1, "Tom Hanks Updated", "updated_photo.jpg");

        verify(actorRepository, times(1)).findById(1);
        verify(actorRepository, times(1)).save(any(Actor.class));
    }

    @Test
    @DisplayName("Should throw NOT_FOUND exception when try patch actor by ID that not exists")
    void shouldThrowNotFoundExceptionWhenTryPatchActorByIdThatNotExists() {
        ActorRequestDto updateDto = new ActorRequestDto("Updated Name", "updated_photo.jpg");

        when(actorRepository.findById(999)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> actorService.patch(999, updateDto))
                .isInstanceOf(DefaultException.class)
                .hasMessage("Actor not found with id: 999");

        verify(actorRepository, times(1)).findById(999);
        verify(actorRepository, never()).save(any(Actor.class));
    }

    @Test
    @DisplayName("Should delete an actor")
    void shouldDeleteActor() {
        when(actorRepository.findById(1)).thenReturn(Optional.of(actor1));

        actorService.delete(1);

        verify(actorRepository, times(1)).findById(1);
        assertThat(actor1.getActive()).isFalse();
        verify(actorRepository, times(1)).save(actor1);
    }

    @Test
    @DisplayName("Should throw NOT_FOUND exception when try delete actor by ID that not exists")
    void shouldThrowNotFoundExceptionWhenTryDeleteActorByIdThatNotExists() {
        when(actorRepository.findById(999)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> actorService.delete(999))
                .isInstanceOf(DefaultException.class)
                .hasMessage("Actor not found with id: 999");

        verify(actorRepository, times(1)).findById(999);
        verify(actorRepository, never()).save(any(Actor.class));
    }
}
