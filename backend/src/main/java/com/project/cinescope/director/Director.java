package com.project.cinescope.director;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.cinescope.movie.Movie;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLRestriction;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "director")
@Getter
@NoArgsConstructor
@SQLRestriction("active = true")
public class Director {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 255, nullable = false)
    private String name;

    @JsonIgnore
    private Boolean active = true;

    @ManyToMany
    @JoinTable(
            name="movie_director",
            joinColumns = @JoinColumn(name = "director_id", table = "director", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id", table = "movie", referencedColumnName = "id")
    )
    private List<Movie> movies = new ArrayList<>();

    public Director(String name) {
        this.name = name;
    }
}
