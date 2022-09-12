package com.example.projectsem2.service.impl;

import com.example.projectsem2.model.Order;
import com.example.projectsem2.repository.OrderRepository;
import com.example.projectsem2.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public Long countAllOrderStatusPending() {
        return orderRepository.countAllOrderStatusPending();
    }

    public Long countAllOrderStatusCancelled() {
        return orderRepository.countAllOrderStatusCancelled();
    }

    public Page<Order> pagination(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 4, Sort.by(Sort.Direction.DESC, "id"));
        return orderRepository.findAll(pageable);
    }
}
