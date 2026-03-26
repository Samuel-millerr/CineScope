package com.project.cinescope.review;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.cinescope.movie.Movie;
import com.project.cinescope.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;

@Entity
@Table(name = "review")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@SQLRestriction("active = true")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_movie", nullable = false, referencedColumnName = "id")
    private Movie movie;

    @Column(columnDefinition = "TEXT", nullable = true)
    private String reviewText;

    @Column(nullable = false)
    private Float reviewRatting;

    @Column(nullable = false)
    private LocalDateTime reviewDate = LocalDateTime.now();

    @JsonIgnore
    private Boolean active = true;
}