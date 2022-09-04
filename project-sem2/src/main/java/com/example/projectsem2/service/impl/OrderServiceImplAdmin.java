package com.example.projectsem2.service.impl;

import com.example.projectsem2.model.Order;
import com.example.projectsem2.repository.OrderRepository;
import com.example.projectsem2.service.CrudService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImplAdmin implements CrudService<Order> {
    @Autowired
    OrderRepository orderRepository;

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public void save(Order order) {
        orderRepository.save(order);
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public Optional<Order> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {

    }

    public List<Order> findAllByOrderByIdDesc() {
        return orderRepository.findAllByOrderByIdDesc();
    }

    public List<Order> findAllOrderWhereStatusDone() {
        return orderRepository.findAllOrderWhereStatusDone();
    }

    public Long countAllOrder() {
        return orderRepository.countAllOrder();
    }

    public Long countAllOrderStatusDone() {
        return orderRepository.countAllOrderStatusDone();
    }

    public Long countAllOrderStatusNotDone() {
        return orderRepository.countAllOrderStatusNotDone();
    }
}
