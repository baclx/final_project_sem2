package com.example.projectsem2.service.impl;

import com.example.projectsem2.model.Sale;
import com.example.projectsem2.repository.SaleRepository;
import com.example.projectsem2.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaleServiceImplAdmin implements CrudService<Sale> {
    @Autowired
    SaleRepository saleRepository;

    @Override
    public List<Sale> findAll() {
        return saleRepository.findAll();
    }

    @Override
    public void save(Sale sale) {

    }

    @Override
    public Optional<Sale> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Sale> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {

    }
}
