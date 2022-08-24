package com.example.projectsem2.service.impl;

import com.example.projectsem2.model.User;
import com.example.projectsem2.repository.UserRepository;
import com.example.projectsem2.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements CrudService<User> {
    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> FindAll() {
        return userRepository.findAll();
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
