package com.example.projectsem2.repository;

import com.example.projectsem2.model.Product;
import com.example.projectsem2.model.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query(value = "select p from Product p order by p.createdAt DESC")
    Page<Product> getAllProductOrderByDay(PageRequest of);

    @Query(value = "select p from Product p inner join Category c on c.id = p.categoryByCategoryId.id where c.name=?1")
    List<Product> getAllByCategory(String category);
    @Query(value = "select p from Product  p inner join Brand  b on b.id = p.brandByBrandId.id where b.name =?1")
    List<Product> getAllByBrand(String brand);
}
