package com.project.cinescope.movie;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalTime;
import java.time.Year;

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

    public Movie(String name, LocalTime duration, Year publicationYear, String synopsis, String poster) {
        this.name = name;
        this.duration = duration;
        this.publicationYear = publicationYear;
        this.synopsis = synopsis;
        this.poster = poster;
    }
}
