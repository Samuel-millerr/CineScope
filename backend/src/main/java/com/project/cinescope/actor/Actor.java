package com.project.cinescope.actor;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "actor")
@Getter
@NoArgsConstructor
@SQLRestriction("active = true")
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255, nullable = false) // Atributo de nome na tabela
    private String name;

    @Column(length = 512, nullable = false)
    private String photo;

    private Boolean active;

    public Actor(String name, String photo) {
        this.name = name;
        this.photo = photo;
        this.active = true;
    }
}
