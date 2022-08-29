package com.example.projectsem2.service.implement;

import com.example.projectsem2.model.Order;
import com.example.projectsem2.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Override
    public List<Order> getAllOrder() {
        return null;
    }

    @Override
    public ResponseEntity<Order> getOrderById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<Order> updateOrderById(Order order, Long id) {
        return null;
    }

    @Override
    public ResponseEntity<Order> deleteOrderById(Long id) {
        return null;
    }

    @Override
    public Order saveOrder(Order order) {
        return null;
    }
}
