package com.project.cinescope.features.actor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.cinescope.features.movie.Movie;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "actor")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SQLRestriction("active = true")
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100, nullable = false, unique = true)
    private String name;

    @Column(length = 512, nullable = false)
    private String photo;

    @JsonIgnore
    private Boolean active = true;

    @ManyToMany
    @JoinTable(
            name = "movie_actor",
            joinColumns = @JoinColumn(name = "id_actor", table = "actor", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_movie", table = "movie", referencedColumnName = "id")
    )
    private List<Movie> movies = new ArrayList<>();
}
