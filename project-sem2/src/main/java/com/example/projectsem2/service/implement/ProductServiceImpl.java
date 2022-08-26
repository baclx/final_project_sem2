package com.example.projectsem2.service.implement;

import com.example.projectsem2.model.Product;
import com.example.projectsem2.repository.ProductRepository;
import com.example.projectsem2.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> getAllNewProduct() {
        return null;
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getAllByCategory(String category) {
        return productRepository.getAllByCategory(category);
    }

    @Override
    public List<Product> getAllByBrand(String brand) {
        return productRepository.getAllByBrand(brand);
    }
}
