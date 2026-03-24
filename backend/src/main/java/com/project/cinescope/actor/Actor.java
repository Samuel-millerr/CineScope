package com.project.cinescope.actor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.cinescope.movie.Movie;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "actor")
@Getter
@NoArgsConstructor
@SQLRestriction("active = true")
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255, nullable = false) // Atributo de nome na tabela
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

    public Actor(String name, String photo) {
        this.name = name;
        this.photo = photo;
    }
}
