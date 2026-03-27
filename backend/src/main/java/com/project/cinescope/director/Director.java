package com.project.cinescope.director;

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
@Table(name = "director")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SQLRestriction("active = true")
public class Director {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100, nullable = false)
    private String name;

    @JsonIgnore
    private Boolean active = true;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "movie_director",
            joinColumns = @JoinColumn(name = "id_director", table = "director", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_movie", table = "movie", referencedColumnName = "id")
    )
    private List<Movie> movies = new ArrayList<>();
}
