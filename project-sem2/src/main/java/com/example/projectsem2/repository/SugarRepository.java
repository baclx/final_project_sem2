package com.example.projectsem2.repository;

import com.example.projectsem2.model.Sugar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SugarRepository extends JpaRepository<Sugar,Integer> {
    Sugar findByPercent(String percent);
}
