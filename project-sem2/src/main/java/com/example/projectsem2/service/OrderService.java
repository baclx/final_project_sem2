package com.example.projectsem2.service;


import com.example.projectsem2.model.Order;
import com.example.projectsem2.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderService {
    List<Order> getAllOrder();
    ResponseEntity<Order> getOrderById(Long id);
    ResponseEntity<Order> updateOrderById(Order orders, Long id);
    Order updateOrder(Order orders, Long id);
    ResponseEntity<Order> deleteOrderById(Long id);
    Order saveOrder(Order orders);

    List<Order> getAllByUser(Long userId);

    List<Order> getHistoryOrder(Long userId);

}
