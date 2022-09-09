package com.example.projectsem2.controller.view;

import com.example.projectsem2.model.*;
import com.example.projectsem2.repository.*;
import com.example.projectsem2.service.impl.UserServiceImplAdmin;
import com.example.projectsem2.service.implement.OrderDetailServiceImp;
import com.example.projectsem2.service.implement.OrderServiceImpl;
import com.example.projectsem2.service.implement.ProductServiceImpl;
import com.example.projectsem2.service.implement.ShoppingCardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

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
    @Autowired
    ShoppingCardServiceImpl shoppingCardService;
    @Autowired
    PaymentRepository paymentRepository;


    @GetMapping("/addOrder")
    public String addOrder(@RequestParam List<Long> cardIds, @RequestParam Long userId){
        System.out.println(cardIds);
        List<ShoppingCard> shoppingCards = new ArrayList<>();
        cardIds.forEach(id->{
            ShoppingCard shoppingCard = shoppingCardService.findById(id);
            shoppingCards.add(shoppingCard);
        });
        Order order = new Order();
        User user = userService.findById(userId).get();
        Payment payment = paymentRepository.findByType("Thanh toán trực tiếp");
        order.setPaymentByPaymentId(payment);
        order.setUserByUserId(user);
        order.setStatusByStatusId(statusRepository.findByName("pending"));
        orderService.saveOrder(order);
        List<OrderDetail> orderDetails = new ArrayList<>();
        shoppingCards.forEach(shoppingCard -> {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setPrice(shoppingCard.getCardPrice());
            orderDetail.setQuantity(shoppingCard.getProductQuantity());
            Ice ice = iceRepository.findByPercent(shoppingCard.getIce());
            orderDetail.setIceByIceId(ice);
            Sugar sugar = sugarRepository.findByPercent(shoppingCard.getSugar());
            orderDetail.setSugarBySugarId(sugar);
            Product product = productService.findByName(shoppingCard.getProductName());
            orderDetail.setProductByProductId(product);
            ProductSize size = productSizeRepository.findByName(shoppingCard.getSize());
            orderDetail.setSizeBySizeId(size);
            Topping topping = toppingRepository.findByTopping(shoppingCard.getTopping());
            orderDetail.setToppingByToppingId(topping);
            orderDetail.setOrderByOrderId(order);
            orderDetailService.saveOrderDetail(orderDetail);
            orderDetails.add(orderDetail);
        });
        cardIds.forEach(id->{
            shoppingCardService.deleteShoppingCardById(id);
        });
        return "redirect:/index";
    }

}
