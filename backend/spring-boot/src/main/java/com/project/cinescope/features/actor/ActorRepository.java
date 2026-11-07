package com.project.cinescope.features.actor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer> {
    boolean existsByName(String name);
}
