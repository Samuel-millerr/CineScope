package com.project.cinescope.features.director;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Integer> {
    boolean existsByName(String name);
}
