package com.example.projectsem2.repository;

import com.example.projectsem2.model.ShoppingCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingCardRepository extends JpaRepository<ShoppingCard,Long> {
    List<ShoppingCard> getAllByUserId(Long userId);
}
