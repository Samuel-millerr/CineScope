package com.project.cinescope.review;

import com.project.cinescope.movie.Movie;
import com.project.cinescope.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;

@Entity
@Table(name = "review")
@NoArgsConstructor
@Getter
@SQLRestriction("active = true")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_movie", nullable = false, referencedColumnName = "id")
    private Movie movie;

    @Column(length = 255, nullable = true)
    private String reviewText;

    @Column(nullable = false)
    private Float reviewRatting;

    @Column(nullable = false)
    private LocalDateTime reviewDate = LocalDateTime.now();

    public Review(User user, Movie movie, String reviewText, Float reviewRatting, LocalDateTime reviewDate) {
        this.user = user;
        this.movie = movie;
        this.reviewText = reviewText;
        this.reviewRatting = reviewRatting;
    }
}