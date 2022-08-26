package com.example.projectsem2.service;

import com.example.projectsem2.model.Review;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviewByProductId(Long productId);
    List<Review> getAllReview();
}
