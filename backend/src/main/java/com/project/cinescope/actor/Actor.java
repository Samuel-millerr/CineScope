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
@AllArgsConstructor
@NoArgsConstructor
@SQLRestriction("active = true")
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false, unique = true)
    private String name;

    @Column(length = 512, nullable = false)
    private String photo;

    @JsonIgnore
    private Boolean active = true;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "movie_actor",
            joinColumns = @JoinColumn(name = "id_actor", table = "actor", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_movie", table = "movie", referencedColumnName = "id")
    )
    private List<Movie> movies = new ArrayList<>();
}
