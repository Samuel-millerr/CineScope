package com.project.cinescope.application.review;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.cinescope.application.movie.Movie;
import com.project.cinescope.application.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;

@Entity
@Table(name = "review")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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

    @Column(name = "review_text", columnDefinition = "TEXT", nullable = true)
    private String reviewText;

    @Column(name = "review_rating", nullable = false)
    private Float reviewRating;

    @Column(name = "review_date", nullable = false)
    private LocalDateTime reviewDate = LocalDateTime.now();

    @JsonIgnore
    private Boolean active = true;
}