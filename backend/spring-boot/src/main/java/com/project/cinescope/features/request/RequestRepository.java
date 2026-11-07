package com.project.cinescope.features.request;

import com.project.cinescope.features.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {
    List<Request> findByUser(User user);
}
