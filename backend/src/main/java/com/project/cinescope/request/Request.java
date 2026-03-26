package com.project.cinescope.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.cinescope.movie.Movie;
import com.project.cinescope.request.enums.RequestStatus;
import com.project.cinescope.request.enums.RequestType;
import com.project.cinescope.user.User;
import com.sun.jdi.ClassType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDate;

@Entity
@Table(name = "request")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@SQLRestriction("active = true")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false, referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_movie", nullable = false, referencedColumnName = "id")
    private Movie movie;

    @Enumerated(EnumType.STRING)
    @Column(name = "request_type", nullable = false)
    private RequestType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "request_status", nullable = false)
    private RequestStatus status = RequestStatus.PENDING;

    @Column(nullable = false)
    private LocalDate requestDate = LocalDate.now();

    @Column(nullable = true, columnDefinition = "json")
    private String requestBody;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String comment;

    @JsonIgnore
    private Boolean active = true;
}
