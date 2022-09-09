package com.example.projectsem2.repository;

import com.example.projectsem2.model.Topping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToppingRepository extends JpaRepository<Topping,Long> {
    Topping findByTopping(String topping);
}
