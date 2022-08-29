package com.example.projectsem2.service;

import com.example.projectsem2.model.Order;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderService {
    List<Order> getAllOrder();
    ResponseEntity<Order> getOrderById(Long id);
    ResponseEntity<Order> updateOrderById(Order order, Long id);
    ResponseEntity<Order> deleteOrderById(Long id);
    Order saveOrder(Order order);
}
