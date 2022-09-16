package com.example.projectsem2.service.implement;

import com.example.projectsem2.model.Category;
import com.example.projectsem2.repository.CategoryRepository;
import com.example.projectsem2.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll(){
        return categoryRepository.findAll();
    }
}
