package com.example.projectsem2.repository;

import com.example.projectsem2.model.ProductSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSizeRepository extends JpaRepository<ProductSize,Integer> {
    ProductSize findByName(String name);

}
