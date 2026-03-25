package com.project.cinescope.genre;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.cinescope.movie.Movie;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLRestriction;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "genre")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@SQLRestriction("active = true")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255, nullable = false)
    public String genre;

    @JsonIgnore
    private Boolean active = true;

    @ManyToMany
    @JoinTable(
            name = "genre_movie",
            joinColumns = @JoinColumn(name = "id_genre", table = "genre", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_movie", table = "movie", referencedColumnName = "id")
    )
    private List<Movie> movies = new ArrayList<>();
}
