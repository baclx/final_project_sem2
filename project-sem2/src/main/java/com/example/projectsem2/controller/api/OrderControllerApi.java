package com.example.projectsem2.controller.api;

import com.example.projectsem2.model.Order;
import com.example.projectsem2.service.impl.OrderServiceImplAdmin;
import com.example.projectsem2.service.implement.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderControllerApi {
    @Autowired
    OrderServiceImpl orderService;

    @PutMapping("/update/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable("id") Long id, Order order){
        return orderService.updateOrderById(order,id);
    }
}
