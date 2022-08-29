package com.example.projectsem2.service.implement;

import com.example.projectsem2.model.Review;
import com.example.projectsem2.repository.ReviewRepository;
import com.example.projectsem2.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    ReviewRepository reviewRepository;
    @Override
    public List<Review> getAllReviewByProductId(Long productId) {
        List<Long> reviewIds = reviewRepository.getReviewIdByProductId(productId);
        List<Review> reviews = new ArrayList<>();
        reviewIds.forEach(rId->{
            Review review = reviewRepository.findById(rId).get();
            reviews.add(review);
        });
        return reviews;
    }

    @Override
    public List<Review> getAllReview() {
        return reviewRepository.findAll();
    }

}
