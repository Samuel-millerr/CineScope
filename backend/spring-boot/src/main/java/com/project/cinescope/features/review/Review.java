package com.project.cinescope.features.review;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.cinescope.features.movie.Movie;
import com.project.cinescope.features.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDate;

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
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_movie", nullable = false, referencedColumnName = "id")
    private Movie movie;

    @Column(name = "review_text", columnDefinition = "TEXT", nullable = true)
    private String reviewText;

    @Column(name = "review_rating", nullable = false)
    private Double reviewRating;

    @Column(name = "review_date", nullable = false)
    private LocalDate reviewDate = LocalDate.now();

    @JsonIgnore
    private Boolean active = true;
}