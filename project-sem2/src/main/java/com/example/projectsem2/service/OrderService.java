package com.example.projectsem2.service;

import com.example.projectsem2.model.Orders;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderService {
    List<Orders> getAllOrder();
    ResponseEntity<Orders> getOrderById(Long id);
    ResponseEntity<Orders> updateOrderById(Orders orders, Long id);
    ResponseEntity<Orders> deleteOrderById(Long id);
    Orders saveOrder(Orders orders);
}
