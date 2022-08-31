package com.example.projectsem2.controller;

import com.example.projectsem2.model.Product;
import com.example.projectsem2.service.implement.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    ProductServiceImpl productService;

    @GetMapping()
    public List<Product> getAllProduct(){
        return productService.getAllProduct();
    }
}
