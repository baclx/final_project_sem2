package com.example.projectsem2.controller.view;

import com.example.projectsem2.dto.OrderDemo;
import com.example.projectsem2.model.*;
import com.example.projectsem2.repository.*;
import com.example.projectsem2.service.OrderService;
import com.example.projectsem2.service.impl.UserServiceImplAdmin;
import com.example.projectsem2.service.implement.OrderDetailServiceImp;
import com.example.projectsem2.service.implement.OrderServiceImpl;
import com.example.projectsem2.service.implement.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrderControllerView {
    @Autowired
    OrderServiceImpl orderService;
    @Autowired
    OrderDetailServiceImp orderDetailService;
    @Autowired
    UserServiceImplAdmin userService;
    @Autowired
    StatusRepository statusRepository;
    @Autowired
    ProductServiceImpl productService;
    @Autowired
    IceRepository iceRepository;
    @Autowired
    SugarRepository sugarRepository;

    @Autowired
    ProductSizeRepository productSizeRepository;
    @Autowired
    ToppingRepository toppingRepository;


    @GetMapping("/saveOrder")
    public String addOrder(@RequestParam OrderDemo demo){
        Order order = new Order();
        User user = userService.findById(demo.getUserId()).get();
        order.setUserByUserId(user);
        order.setStatusByStatusId(statusRepository.findByName("pending"));
        orderService.saveOrder(order);
        System.out.println(order);
        OrderDetail orderDetail = new OrderDetail();
        Product product = productService.findByName(demo.getProductName());
        orderDetail.setOrderByOrderId(order);
        orderDetail.setProductByProductId(product);
        orderDetail.setQuantity(1);
        orderDetail.setIceByIceId(iceRepository.findByPercent(demo.getIce()));
        orderDetail.setSugarBySugarId(sugarRepository.findByPercent(demo.getSugar()));
        orderDetail.setSizeBySizeId(productSizeRepository.findByName(demo.getSize()));
        orderDetail.setToppingByToppingId(toppingRepository.findByTopping(demo.getTopping()));
        orderDetailService.saveOrderDetail(orderDetail);
        return "index";
    }

}
