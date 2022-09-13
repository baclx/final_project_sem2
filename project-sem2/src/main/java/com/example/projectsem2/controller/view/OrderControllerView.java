package com.example.projectsem2.controller.view;

import com.example.projectsem2.controller.mail.MailService;
import com.example.projectsem2.dto.OrderInput;
import com.example.projectsem2.model.*;
import com.example.projectsem2.repository.*;
import com.example.projectsem2.service.impl.UserServiceImplAdmin;
import com.example.projectsem2.service.implement.OrderDetailServiceImp;
import com.example.projectsem2.service.implement.OrderServiceImpl;
import com.example.projectsem2.service.implement.ProductServiceImpl;
import com.example.projectsem2.service.implement.ShoppingCardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class OrderControllerView {
    // Mail
    @Autowired
    MailService mailService;
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

    public Long getcurrentUserId(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();

        } else {
            username = principal.toString();
        }
        Optional<User> opUsert = userService.findByUsername(username);
        User u;
        if(opUsert.isPresent()) {
            u = opUsert.get();
        } else {
            u = new User();
        }
        Long currentUserId = u.getId();
        return currentUserId;
    }
    @PostMapping("/addOrder")
    public String addOrder(@ModelAttribute OrderInput orderInput, @RequestParam List<Long> cardIds){
        String address = orderInput.getAddress();
        Long userId = orderInput.getUserId();
        List<ShoppingCard> shoppingCards = new ArrayList<>();
        cardIds.forEach(id->{
            ShoppingCard shoppingCard = shoppingCardService.findById(id);
            if(shoppingCard.getProductQuantity()==0){
                shoppingCardService.deleteShoppingCardById(id);
            }else{
                shoppingCards.add(shoppingCard);
            }
        });
        if(shoppingCards.size()==0){
            return "redirect:/orderTracking/user/"+getcurrentUserId();
        }
        Order order = new Order();
        User user = userService.findById(userId).get();
        Payment payment = paymentRepository.findByType("Thanh toán trực tiếp");
        order.setAddress(address);
        order.setPaymentByPaymentId(payment);
        order.setUserByUserId(user);
        order.setStatusByStatusId(statusRepository.findByName("Pending"));
        orderService.saveOrder(order);
        List<OrderDetail> orderDetails = new ArrayList<>();
        shoppingCards.forEach(shoppingCard -> {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setPrice(shoppingCard.getProductQuantity()*shoppingCard.getProductPrice());
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
        mailService.sendSimpleMail();
        return "redirect:/orderTracking/user/"+getcurrentUserId();

    }

    @GetMapping("/orderTracking/user/{id}")
    public String getOrderTracking(@PathVariable("id") Long id, Model model) {
        Long currentUserId = getcurrentUserId();
        model.addAttribute("currentUserId", currentUserId);
        User currentUser = userService.getUserById(currentUserId).getBody();
        model.addAttribute("currentUser", currentUser);
        List<Order> orders = orderService.getAllByUser(currentUserId);
        model.addAttribute("orders",orders);
        return "orderTracking";
    }

    @GetMapping("/updateOrder")
    public String updateOrder(@RequestParam Long orderId, @RequestParam Long userId){
        Order order = orderService.getOrderById(orderId).getBody();
        Status status = statusRepository.findByName("Received");
        order.setStatusByStatusId(status);
        orderService.saveOrder(order);
        return "redirect:/orderTracking/user/"+userId;
    }

    @GetMapping("/historyOrder/user/{id}")
    public String history(@PathVariable("id") Long id, Model model){
        Long currentUserId = getcurrentUserId();
        model.addAttribute("currentUserId", currentUserId);
        User currentUser = userService.getUserById(currentUserId).getBody();
        model.addAttribute("currentUser", currentUser);
        List<Order> orders = orderService.getHistoryOrder(currentUserId);
        model.addAttribute("orders",orders);
        return "history";
    }

}
