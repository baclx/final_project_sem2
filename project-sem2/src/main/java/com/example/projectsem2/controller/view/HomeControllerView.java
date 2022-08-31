package com.example.projectsem2.controller.view;

import com.example.projectsem2.model.Product;
import com.example.projectsem2.model.User;
import com.example.projectsem2.service.UserService;
import com.example.projectsem2.service.impl.UserServiceImpl;
import com.example.projectsem2.service.implement.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class HomeControllerView {
    @Autowired
    ProductServiceImpl productService;
    @Autowired
    UserServiceImpl userService;
    public Long getcurrentUserId(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email;
        if (principal instanceof UserDetails) {
            email = ((UserDetails) principal).getUsername();

        } else {
            email = principal.toString();
        }
        Optional<com.example.projectsem2.model.User> opUsert = userService.findByEmail(email);
        com.example.projectsem2.model.User u;
        if(opUsert.isPresent()) {
            u = opUsert.get();
        } else {
            u = new User();
        }
        Long currentUserId = u.getId();
        return currentUserId;
    }
    @GetMapping(value = {"/","/index","/home"})
    public String showIndex(Model model){
        List<Product> products = productService.getAllProduct();
        model.addAttribute("products",products);
        System.out.println(products);
        User currentUser = userService.findById(getcurrentUserId()).get();
        model.addAttribute("currentUser",currentUser);
        Long currentUserId = getcurrentUserId();
        model.addAttribute("currentUserId",currentUserId);
        return "index";
    }

}
