package com.example.projectsem2.controller.admin;

import com.example.projectsem2.model.Category;
import com.example.projectsem2.model.Product;
import com.example.projectsem2.model.User;
import com.example.projectsem2.service.ProductService;
import com.example.projectsem2.service.impl.CategoryServiceImplAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    CategoryServiceImplAdmin categoryService;

    @GetMapping("")
    public String index(
            Model model
    ) {
        List<Product> productLists = productService.getAllProduct();

        model.addAttribute("productLists", productLists);
        model.addAttribute("title", "Product");

        return "admin/product/index";
    }

    @GetMapping("/add")
    public String add(
            Model model
    ) {
        List<Category> categoryLists = categoryService.findAll();

        model.addAttribute("product", new Product());
        model.addAttribute("title", "Add Product");
        model.addAttribute("categoryLists", categoryLists);

        return "admin/product/create";
    }

    @PostMapping("/store")
    public String store(
            Product product,
            RedirectAttributes ra
    ) {
        ra.addFlashAttribute("msg", "Create Success");

        productService.save(product);

        return "redirect:/admin/product";
    }
}
