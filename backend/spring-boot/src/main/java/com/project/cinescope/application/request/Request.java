package com.project.cinescope.application.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.cinescope.application.movie.Movie;
import com.project.cinescope.application.movie.request.MovieRequestDto;
import com.project.cinescope.application.request.enums.RequestStatus;
import com.project.cinescope.application.request.enums.RequestType;
import com.project.cinescope.application.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDate;

@Entity
@Table(name = "request")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
    private RequestStatus status = RequestStatus.PENDENTE;

    @Column(nullable = false)
    private LocalDate requestDate = LocalDate.now();

    @Column(columnDefinition = "json")
    private MovieRequestDto requestBody;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String comment;

    @JsonIgnore
    private Boolean active = true;
}
