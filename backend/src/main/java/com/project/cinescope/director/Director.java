package com.project.cinescope.director;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "director")
@Getter
@NoArgsConstructor
@SQLRestriction("active = true")
public class Director {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 255, nullable = false)
    private String name;

    @JsonIgnore
    private Boolean active = true;

    public Director(String name) {
        this.name = name;
    }
}
