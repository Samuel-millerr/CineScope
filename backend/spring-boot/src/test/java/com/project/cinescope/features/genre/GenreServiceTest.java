package com.project.cinescope.features.genre;

import com.project.cinescope.core.exception.DefaultException;
import com.project.cinescope.features.genre.impl.GenreServiceImpl;
import com.project.cinescope.features.genre.request.GenreRequestDTO;
import com.project.cinescope.features.genre.response.GenreResponseDTO;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GenreServiceTest {

    @Mock
    private GenreRepository genreRepository;

    @Mock
    private GenreMapper genreMapper;

    @InjectMocks
    private GenreServiceImpl genreService;

    private Genre genre1;
    private Genre genre2;
    private Genre genre3;

    @BeforeEach
    void setUp() {
        genre1 = new Genre(1, "Action", true, null);
        genre2 = new Genre(2, "Comedy", true, null);
        genre3 = new Genre(3, "Drama", true, null);
    }

    @Test
    @DisplayName("Should return all genres")
    void shouldReturnAllGenres() {
        when(genreRepository.findAll()).thenReturn(Arrays.asList(genre1, genre2, genre3));
        when(genreMapper.toResponse(any(Genre.class))).thenAnswer(invocation -> {
            Genre genre = invocation.getArgument(0);
            return new GenreResponseDTO(genre.getId(), genre.getGenre());
        });

        List<GenreResponseDTO> result = genreService.getAll();

        assertThat(result)
                .hasSize(3)
                .extracting(GenreResponseDTO::id, GenreResponseDTO::genre)
                .containsExactly(
                        tuple(1, "Action"),
                        tuple(2, "Comedy"),
                        tuple(3, "Drama")
                );
    }

    @Test
    @DisplayName("Should return a genre by ID")
    void shouldReturnGenreById() {
        when(genreRepository.findById(1)).thenReturn(Optional.of(genre1));
        when(genreMapper.toResponse(genre1)).thenReturn(new GenreResponseDTO(1, "Action"));

        GenreResponseDTO result = genreService.getById(1);

        assertThat(result)
                .isNotNull()
                .extracting(GenreResponseDTO::id, GenreResponseDTO::genre)
                .containsExactly(1, "Action");

        verify(genreRepository, times(1)).findById(1);
    }

    @Test
    @DisplayName("Should throw NOT_FOUND exception when try get genre by ID that not exists")
    void shouldThrowNotFoundExceptionWhenTryGetGenreByIdThatNotExists() {
        when(genreRepository.findById(999)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> genreService.getById(999))
                .isInstanceOf(DefaultException.class)
                .hasMessage("Genre not found with id: 999");

        verify(genreRepository, times(1)).findById(999);
    }

    @Test
    @DisplayName("Should create a new genre")
    void shouldCreateNewGenre() {
        GenreRequestDTO requestDto = new GenreRequestDTO("Sci-Fi");
        Genre newGenre = new Genre(4, "Sci-Fi", true, null);

        when(genreRepository.existsByGenre("Sci-Fi")).thenReturn(false);
        when(genreMapper.toEntity(requestDto)).thenReturn(newGenre);
        when(genreMapper.toResponse(newGenre)).thenReturn(new GenreResponseDTO(4, "Sci-Fi"));
        when(genreRepository.save(any(Genre.class))).thenReturn(newGenre);

        GenreResponseDTO result = genreService.post(requestDto);

        assertThat(result)
                .isNotNull()
                .extracting(GenreResponseDTO::id, GenreResponseDTO::genre)
                .containsExactly(4, "Sci-Fi");

        verify(genreRepository, times(1)).existsByGenre("Sci-Fi");
        verify(genreRepository, times(1)).save(any(Genre.class));
    }

    @Test
    @DisplayName("Should throw CONFLICT exception when try save genre with genre name already exists")
    void shouldThrowConflictExceptionWhenTrySaveGenreWithGenreNameAlreadyExists() {
        GenreRequestDTO requestDto = new GenreRequestDTO("Action");

        when(genreRepository.existsByGenre("Action")).thenReturn(true);

        assertThatThrownBy(() -> genreService.post(requestDto))
                .isInstanceOf(DefaultException.class)
                .hasMessage("Genre already exists with genre: Action");

        verify(genreRepository, times(1)).existsByGenre("Action");
        verify(genreRepository, never()).save(any(Genre.class));
    }

    @Test
    @DisplayName("Should delete an existing genre")
    void shouldDeleteGenre() {
        when(genreRepository.findById(1)).thenReturn(Optional.of(genre1));

        genreService.delete(1);

        verify(genreRepository, times(1)).findById(1);
        assertThat(genre1.getActive()).isFalse();
        verify(genreRepository, times(1)).save(genre1);
    }

    @Test
    @DisplayName("Should throw NOT_FOUND exception when try delete genre by ID that not exists")
    void shouldThrowNotFoundExceptionWhenTryDeleteGenreByIdThatNotExists() {
        when(genreRepository.findById(999)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> genreService.delete(999))
                .isInstanceOf(DefaultException.class)
                .hasMessage("Genre not found with id: 999");

        verify(genreRepository, times(1)).findById(999);
        verify(genreRepository, never()).save(any(Genre.class));
    }
}
