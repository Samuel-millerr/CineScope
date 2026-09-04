package com.project.cinescope.application.director;

import com.project.cinescope.application.director.impl.DirectorServiceImpl;
import com.project.cinescope.application.director.request.DirectorRequestDto;
import com.project.cinescope.application.director.response.DirectorResponseDto;
import com.project.cinescope.core.exception.DefaultException;
import com.project.cinescope.core.exception.exceptions.ResourceNotFoundException;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.groups.Tuple.tuple;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DirectorServiceTest {

    @Mock
    private DirectorRepository directorRepository;

    @InjectMocks
    private DirectorServiceImpl directorService;

    private Director director1;
    private Director director2;
    private Director director3;

    @BeforeEach
    void setUp() {
        director1 = new Director(1, "Steven Spielberg", true, null);
        director2 = new Director(2, "Christopher Nolan", true, null);
        director3 = new Director(3, "Quentin Tarantino", true, null);
    }

    @Test
    @DisplayName("Should return all directors mapped to response DTOs")
    void shouldReturnMultipleDirectors() {
        when(directorRepository.findAll()).thenReturn(Arrays.asList(director1, director2, director3));

        List<DirectorResponseDto> result = directorService.getAll();

        assertThat(result)
                .hasSize(3)
                .extracting(DirectorResponseDto::id, DirectorResponseDto::name)
                .containsExactly(
                        tuple(1, "Steven Spielberg"),
                        tuple(2, "Christopher Nolan"),
                        tuple(3, "Quentin Tarantino")
                );
    }

    @Test
    @DisplayName("Should return an director by ID")
    void shouldReturnDirectorById() {
        when(directorRepository.findById(1)).thenReturn(Optional.of(director1));

        DirectorResponseDto result = directorService.getById(1);

        assertThat(result)
                .isNotNull()
                .extracting(DirectorResponseDto::id, DirectorResponseDto::name)
                .containsExactly(1, "Steven Spielberg");

        verify(directorRepository, times(1)).findById(1);
    }

    @Test
    @DisplayName("Should throw ResourceNotFoundException when director not found by ID")
    void shouldThrowNotFoundExceptionWhenDirectorDoesNotExist() {
        when(directorRepository.findById(999)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> directorService.getById(999))
                .isInstanceOf(DefaultException.class)
                .hasMessage("Director not found with id: 999");

        verify(directorRepository, times(1)).findById(999);
    }

    @Test
    @DisplayName("Should create a new director")
    void shouldCreateNewDirector() {
        DirectorRequestDto requestDto = new DirectorRequestDto("James Cameron");
        Director newDirector = new Director(4, "James Cameron", true, null);

        when(directorRepository.existsByName("James Cameron")).thenReturn(false);
        when(directorRepository.save(any(Director.class))).thenReturn(newDirector);

        DirectorResponseDto result = directorService.post(requestDto);

        assertThat(result)
                .isNotNull()
                .extracting(DirectorResponseDto::id, DirectorResponseDto::name)
                .containsExactly(4, "James Cameron");

        verify(directorRepository, times(1)).existsByName("James Cameron");
        verify(directorRepository, times(1)).save(any(Director.class));
    }

    @Test
    @DisplayName("Should throw DuplicateResourceException when director name already exists")
    void shouldThrowDuplicateExceptionWhenDirectorAlreadyExists() {
        DirectorRequestDto requestDto = new DirectorRequestDto("Steven Spielberg");

        when(directorRepository.existsByName("Steven Spielberg")).thenReturn(true);

        assertThatThrownBy(() -> directorService.post(requestDto))
                .isInstanceOf(DefaultException.class)
                .hasMessage("Director already exists with name: Steven Spielberg");

        verify(directorRepository, times(1)).existsByName("Steven Spielberg");
        verify(directorRepository, never()).save(any(Director.class));
    }

    @Test
    @DisplayName("Should update an existing director")
    void shouldUpdateExistingDirector() {
        DirectorRequestDto updateDirector = new DirectorRequestDto("Steven Spielberg Updated");
        Director existingDirector = new Director(1, "Steven Spielberg", true, null);
        Director updatedDirector = new Director(1, "Steven Spielberg Updated", true, null);

        when(directorRepository.findById(1)).thenReturn(Optional.of(existingDirector));
        when(directorRepository.save(any(Director.class))).thenReturn(updatedDirector);

        DirectorResponseDto result = directorService.patch(1, updateDirector);

        assertThat(result)
                .isNotNull()
                .extracting(DirectorResponseDto::id, DirectorResponseDto::name)
                .containsExactly(1, "Steven Spielberg Updated");

        verify(directorRepository, times(1)).findById(1);
        verify(directorRepository, times(1)).save(any(Director.class));
    }

    @Test
    @DisplayName("Should throw ResourceNotFoundException when updating a non-existing director")
    void shouldThrowNotFoundWhenPatchingExistentDirector() {
        DirectorRequestDto updateDto = new DirectorRequestDto("Non-Existent Director");

        when(directorRepository.findById(999)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> directorService.patch(999, updateDto))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Director not found with id: 999");

        verify(directorRepository, times(1)).findById(999);
        verify(directorRepository, never()).save(any(Director.class));
    }

    @Test
    @DisplayName("Should delete an existing director")
    void shouldDeleteDirector() {
        when(directorRepository.findById(1)).thenReturn(Optional.of(director1));

        directorService.delete(1);

        verify(directorRepository, times(1)).findById(1);
        assertThat(director1.getActive()).isFalse();
        verify(directorRepository, times(1)).save(director1);
    }

    @Test
    @DisplayName("Should throw ResourceNotFoundException when deleting non-existent director")
    void shouldThrowNotFoundWhenDeletingNonExistentDirector() {
        when(directorRepository.findById(999)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> directorService.delete(999))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Director not found with id: 999");

        verify(directorRepository, times(1)).findById(999);
        verify(directorRepository, never()).save(any(Director.class));
    }
}
