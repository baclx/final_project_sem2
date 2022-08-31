package com.example.projectsem2.service.impl;

import com.example.projectsem2.model.Category;
import com.example.projectsem2.model.Product;
import com.example.projectsem2.repository.ProductRepository;
import com.example.projectsem2.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImplAdmin implements CrudService<Product> {
    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Optional<Product> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public Optional<Product> findProductByCategoryID(Category category) {
        return productRepository.findProductByCategoryID(category);
    }
}
