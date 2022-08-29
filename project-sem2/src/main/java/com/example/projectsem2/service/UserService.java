package com.example.projectsem2.service;

import com.example.projectsem2.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}