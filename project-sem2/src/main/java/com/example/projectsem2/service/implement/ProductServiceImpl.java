package com.example.projectsem2.service.implement;

import com.example.projectsem2.model.Product;
import com.example.projectsem2.repository.OrderDetailRepository;
import com.example.projectsem2.repository.ProductRepository;
import com.example.projectsem2.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrderDetailRepository orderDetailRepository;

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

    public List<Product> getTopSeller(){
        List<Product> products = productRepository.findAll();
        Map<Long,Long> pIdMap = new HashMap<>();
        products.forEach(p->{
            Long total = orderDetailRepository.countByProductByProductId(p);
            pIdMap.put(p.getId(),total);
        });
        //Sorted
        pIdMap.entrySet().stream()
                .sorted((k1, k2) -> -k1.getValue().compareTo(k2.getValue()))
                .forEach(k -> System.out.println(k.getKey() + ": " + k.getValue()));
        List<Product> topProducts = new ArrayList<>();
        pIdMap.forEach((k,v)->{
            topProducts.add(productRepository.findById(k).get());
        });

        return topProducts;
    }
}
