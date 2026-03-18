package com.project.cinescope.genre;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "genre")
@NoArgsConstructor
@Getter
@SQLRestriction("active = true")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255, nullable = false)
    public String genre;

    @JsonIgnore
    private Boolean active = true;

    public Genre(String genre) {
        this.genre = genre;
    }
}
