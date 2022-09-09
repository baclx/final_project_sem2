package com.example.projectsem2.service.impl;

import com.example.projectsem2.model.OrderDetail;
import com.example.projectsem2.repository.OrderDetailRepository;
import com.example.projectsem2.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailServiceImplAdmin {
    @Autowired
    OrderDetailRepository orderDetailRepository;

    public List<OrderDetail> findOrderDetailByOrderId(Long id){
        return orderDetailRepository.findOrderDetailByOrderId(id);
    }
}
