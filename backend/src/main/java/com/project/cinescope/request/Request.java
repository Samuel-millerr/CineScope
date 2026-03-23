package com.project.cinescope.request;

import com.project.cinescope.movie.Movie;
import com.project.cinescope.request.enums.RequestStatus;
import com.project.cinescope.request.enums.RequestType;
import com.project.cinescope.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "request")
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
    private RequestType requestType;

    @Enumerated(EnumType.STRING)
    @Column(name = "request_status", nullable = false)
    private RequestStatus requestStatus = RequestStatus.PENDING;

    @Column(nullable = true, columnDefinition = "json")
    private String requestBody;

    public Request(User user, Movie movie, RequestType requestType, RequestStatus requestStatus, String requestBody) {
        this.user = user;
        this.movie = movie;
        this.requestType = requestType;
        this.requestStatus = requestStatus;
        this.requestBody = requestBody;
    }
}
