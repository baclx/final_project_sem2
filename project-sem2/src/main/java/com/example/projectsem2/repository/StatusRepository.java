package com.example.projectsem2.repository;

import com.example.projectsem2.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Long> {
}
