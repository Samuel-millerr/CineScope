package com.project.cinescope.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.cinescope.user.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@NoArgsConstructor
@Getter
@SQLRestriction("active = true")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255, nullable = false, unique = true)
    private String username;

    @Column(length = 255, nullable = false)
    private String hashedPassword;

    @Column(length = 255, nullable = false)
    private String firstName;

    @Column(length = 255, nullable = false)
    private String lastName;

    @Column(length = 255, nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role", nullable = false)
    private UserRole userRole = UserRole.COMMON;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @JsonIgnore
    private Boolean active = true;

    public User(String username, String hashedPassword, String firstName, String lastName, String email, UserRole userRole) {
        this.username = username;
        this.hashedPassword = hashedPassword;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userRole = userRole;
    }
}
