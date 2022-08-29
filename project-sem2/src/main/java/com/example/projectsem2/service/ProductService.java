package com.example.projectsem2.service;

import com.example.projectsem2.model.Product;
import com.example.projectsem2.model.Review;

import java.util.List;

public interface ProductService {
    List<Product> getAllNewProduct();
    List<Product> getAllProduct();
    List<Product> getAllByCategory(String category);


}

