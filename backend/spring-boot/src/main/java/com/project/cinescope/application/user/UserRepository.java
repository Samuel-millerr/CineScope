package com.project.cinescope.application.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User getByUsername(String username);

    UserDetails findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
