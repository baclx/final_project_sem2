package com.example.projectsem2.controller.view;

import com.example.projectsem2.model.Product;
import com.example.projectsem2.service.implement.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProductControllerView {
    @Autowired
    ProductServiceImpl productService;

    @GetMapping("product/list/new")
    public String getAllProduct(Model model){
        List<Product> products = productService.getAllNewProduct();
        return null;
    }

    @GetMapping({"/index", "/"})
    public String index() {
        return "index";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/product")
    public String product() {
        return "product";
    }

    @GetMapping("/service")
    public String service() {
        return "service";
    }

    @GetMapping("/gallery")
    public String gallery() {
        return "gallery";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }
}
