package com.example.projectsem2.service.impl;

import com.example.projectsem2.model.Category;
import com.example.projectsem2.model.Product;
import com.example.projectsem2.repository.ProductRepository;
import com.example.projectsem2.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductServiceImplAdmin implements CrudService<Product> {
    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public List<Product> sort(
            String sortField,
            String sortDir
    ) {
        if (sortDir.equals("desc")) {
            return productRepository.sortAllProductDesc(sortField);
        }
        return productRepository.sortAllProductAsc(sortField);
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