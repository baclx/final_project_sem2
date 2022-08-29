package com.example.projectsem2.repository;

import com.example.projectsem2.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    @Query(value = "select r.id from Review r inner join Product p ON r.productByProductId.id = p.id where p.id =?1")
    List<Long> getReviewIdByProductId(Long productId);


}
