package com.example.projectsem2.service;

import com.example.projectsem2.model.Product;
import com.example.projectsem2.model.Review;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllNewProduct();
    List<Product> getAllProduct();
    List<Product> getAllByCategory(String category);

    Optional<Product> findByID(Long id);

    void save(Product product);
    List<Product> getAllSale25();
}

