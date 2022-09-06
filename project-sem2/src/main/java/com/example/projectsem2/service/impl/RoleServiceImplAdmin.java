package com.example.projectsem2.service.impl;

import com.example.projectsem2.model.Role;
import com.example.projectsem2.repository.RoleRepository;
import com.example.projectsem2.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImplAdmin implements CrudService<Role> {
    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }

    @Override
    public Optional<Role> findById(Long id) {
        return roleRepository.findById(id);
    }

    @Override
    public Optional<Role> findByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }
}
