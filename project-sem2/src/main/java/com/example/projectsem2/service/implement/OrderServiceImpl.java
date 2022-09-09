package com.example.projectsem2.service.implement;


import com.example.projectsem2.model.Order;
import com.example.projectsem2.repository.OrderRepository;
import com.example.projectsem2.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Override
    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }

    @Override
    public ResponseEntity<Order> getOrderById(Long id) {
        Order order = orderRepository.findById(id).get();

        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Order> updateOrderById(Order orders, Long id) {
        return null;
    }

    @Override
    public ResponseEntity<Order> deleteOrderById(Long id) {
        return null;
    }

    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }
}
