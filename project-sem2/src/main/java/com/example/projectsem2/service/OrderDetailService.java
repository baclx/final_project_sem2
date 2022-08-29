package com.example.projectsem2.service;

import com.example.projectsem2.model.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetail> getAllOrderDetail();
    List<OrderDetail> getReceiptUserId(Long userId);

}
