package com.project.cinescope.movie;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.cinescope.actor.Actor;
import com.project.cinescope.director.Director;
import com.project.cinescope.genre.Genre;
import com.project.cinescope.request.Request;
import com.project.cinescope.review.Review;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalTime;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "movie")
@AllArgsConstructor
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

    @ManyToMany(mappedBy = "movies", targetEntity = Actor.class)
    private List<Actor> actors = new ArrayList<>();

    @ManyToMany(mappedBy = "movies", targetEntity = Director.class)
    private List<Director> directors = new ArrayList<>();

    @ManyToMany(mappedBy = "movies", targetEntity = Genre.class)
    private List<Genre> genres = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movie", targetEntity = Review.class)
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movie", targetEntity = Request.class)
    private List<Request> requests = new ArrayList<>();

    @JsonIgnore
    private Boolean active = true;
}
