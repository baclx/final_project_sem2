package com.example.projectsem2.controller.view;

import com.example.projectsem2.model.Product;
import com.example.projectsem2.service.ProductService;
import com.example.projectsem2.service.implement.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeControllerView {
    @Autowired
    ProductServiceImpl productService;
    @GetMapping(value = {"/","/index","/home"})
    public String showIndex(Model model){
        List<Product> products = productService.getAllProduct();
        model.addAttribute("products",products);
        System.out.println(products);
        return "index";
    }

}
