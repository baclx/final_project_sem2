package com.example.projectsem2.repository;

import com.example.projectsem2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    @Query("SELECT count(u.id) from User u")
    Long countAllUser();
}
