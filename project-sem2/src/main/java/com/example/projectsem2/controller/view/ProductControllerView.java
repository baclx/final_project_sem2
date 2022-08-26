package com.example.projectsem2.controller.view;

import com.example.projectsem2.service.implement.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductControllerView {
    @Autowired
    ProductServiceImpl productService;

    @GetMapping("product/list")
    public String getAllProduct(Model model){

        return null;
    }
}
