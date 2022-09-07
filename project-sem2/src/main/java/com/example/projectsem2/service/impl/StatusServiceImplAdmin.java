package com.example.projectsem2.service.impl;

import com.example.projectsem2.model.Status;
import com.example.projectsem2.repository.StatusRepository;
import com.example.projectsem2.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatusServiceImplAdmin implements CrudService<Status> {
    @Autowired
    StatusRepository statusRepository;

    @Override
    public List<Status> findAll() {
        return statusRepository.findAll();
    }

    @Override
    public void save(Status status) {

    }

    @Override
    public Optional<Status> findById(Long id) {
        return statusRepository.findById(id);
    }

    @Override
    public Optional<Status> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {

    }
}
