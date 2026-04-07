package com.project.cinescope.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.cinescope.request.Request;
import com.project.cinescope.review.Review;
import com.project.cinescope.user.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cinescope_user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@SQLRestriction("active = true")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false, unique = true)
    private String username;

    @Column(length = 100, nullable = false)
    private String hashedPassword;

    @Column(length = 100, nullable = false)
    private String firstName;

    @Column(length = 100, nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role = UserRole.COMUM;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", targetEntity = Review.class)
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", targetEntity = Request.class)
    private List<Request> requests = new ArrayList<>();

    @JsonIgnore
    private Boolean active = true;
}
