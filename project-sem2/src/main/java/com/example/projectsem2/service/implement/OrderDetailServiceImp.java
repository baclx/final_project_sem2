package com.example.projectsem2.service.implement;

import com.example.projectsem2.model.OrderDetail;
import com.example.projectsem2.repository.OrderDetailRepository;
import com.example.projectsem2.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderDetailServiceImp implements OrderDetailService {
    @Autowired
    OrderDetailRepository orderDetailRepository;
    @Override
    public List<OrderDetail> getAllOrderDetail() {
        return orderDetailRepository.findAll();
    }

    @Override
    public List<OrderDetail> getReceiptUserId(Long userId) {
        List<Long> orderDetailIds = orderDetailRepository.getOderDetailIdByUserId(userId);
        List<OrderDetail> orderDetails = new ArrayList<>();
        orderDetailIds.forEach(od->{
            OrderDetail orderDetail = orderDetailRepository.findById(od).get();
            orderDetails.add(orderDetail);
        });
        return orderDetails                                               ;
    }

    @Override
    public OrderDetail saveOrderDetail(OrderDetail orderDetail){
        return orderDetailRepository.save(orderDetail);
    }

}
