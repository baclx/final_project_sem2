package com.example.projectsem2.service;

import java.util.List;
import java.util.Optional;

// T - Type
public interface CrudService<T> {
    List<T> FindAll();

    void save(T t);

    Optional<T> findById(Long id);

    void deleteById(Long id);
}
