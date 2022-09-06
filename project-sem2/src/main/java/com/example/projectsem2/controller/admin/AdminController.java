package com.example.projectsem2.controller.admin;

import com.example.projectsem2.service.impl.ProductServiceImplAdmin;
import com.example.projectsem2.service.impl.UserServiceImplAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    UserServiceImplAdmin userService;

    @Autowired
    ProductServiceImplAdmin productService;

    @GetMapping("")
    public String index(
            Model model
    ) {
        Long allUser = userService.countAllUser();
        Long allProduct = productService.countAllProduct();

        model.addAttribute("allUser", allUser);
        model.addAttribute("allProduct", allProduct);

        return "admin/index";
    }
}
