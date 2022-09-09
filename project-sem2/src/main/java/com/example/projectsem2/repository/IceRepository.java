package com.example.projectsem2.repository;

import com.example.projectsem2.model.Ice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IceRepository extends JpaRepository<Ice,Integer> {
    Ice findByPercent(String percent);
}
