package com.example.projectsem2.service.implement;

import com.example.projectsem2.model.Product;
import com.example.projectsem2.repository.ProductRepository;
import com.example.projectsem2.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> getAllNewProduct() {
        Page<Product> productPage = productRepository.getAllNewProduct(PageRequest.of(0, 6));
        List<Product> products = productPage.getContent();
        return products;
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
    public Optional<Product> findByID(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }
}
