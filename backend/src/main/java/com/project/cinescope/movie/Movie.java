package com.project.cinescope.movie;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.cinescope.actor.Actor;
import com.project.cinescope.director.Director;
import com.project.cinescope.genre.Genre;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalTime;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "movie")
@NoArgsConstructor
@Getter
@SQLRestriction("active = true")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255, nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private LocalTime duration;

    @Column(nullable = false)
    private Year publicationYear;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String synopsis;

    @Column(nullable = false)
    private String poster;

    @JsonIgnore
    private Boolean active = true;

    @ManyToMany(mappedBy = "movies")
    private List<Actor> actors = new ArrayList<>();

    @ManyToMany(mappedBy = "movies")
    private List<Director> directors = new ArrayList<>();

    @ManyToMany(mappedBy = "movies")
    private List<Genre> genres = new ArrayList<>();

    public Movie(String name, LocalTime duration, Year publicationYear, String synopsis, String poster) {
        this.name = name;
        this.duration = duration;
        this.publicationYear = publicationYear;
        this.synopsis = synopsis;
        this.poster = poster;
    }
}
