package com.example.projectsem2.service.implement;

import com.example.projectsem2.model.Orders;
import com.example.projectsem2.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Override
    public List<Orders> getAllOrder() {
        return null;
    }

    @Override
    public ResponseEntity<Orders> getOrderById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<Orders> updateOrderById(Orders orders, Long id) {
        return null;
    }

    @Override
    public ResponseEntity<Orders> deleteOrderById(Long id) {
        return null;
    }

    @Override
    public Orders saveOrder(Orders orders) {
        return null;
    }
}
