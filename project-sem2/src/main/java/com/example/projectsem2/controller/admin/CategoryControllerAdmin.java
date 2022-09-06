package com.example.projectsem2.controller.admin;

import com.example.projectsem2.model.Category;
import com.example.projectsem2.model.Product;
import com.example.projectsem2.service.impl.CategoryServiceImplAdmin;
import com.example.projectsem2.service.impl.ProductServiceImplAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/category")
public class CategoryControllerAdmin {
    @Autowired
    CategoryServiceImplAdmin categoryService;

    @Autowired
    ProductServiceImplAdmin productService;

    @GetMapping("")
    public String index(
            Model model
    ) {
        List<Category> categoryLists = categoryService.findAll();
        model.addAttribute("categoryLists", categoryLists);
        model.addAttribute("title", "Category");
        return "admin/category/index";
    }

    @GetMapping("/add")
    public String add(
            Model model
    ) {
        model.addAttribute("category", new Category());
        model.addAttribute("title", "Create Category");

        return "admin/category/create";
    }

    @PostMapping("/store")
    public String store(
            Category category,
            RedirectAttributes ra
    ) {
        categoryService.save(category);
        ra.addFlashAttribute("msg","Create Success");

        return "redirect:/admin/category";
    }

    @GetMapping("/delete/{id}")
    public String delete(
            @PathVariable("id") Long id,
            RedirectAttributes ra,
            Category category
    ) {
        Optional<Category> categoryOptional = categoryService.findById(id);
        Optional<Product> product = productService.findProductByCategoryID(category);

        if (product.isPresent()) {
            ra.addFlashAttribute("wn", "category nay` co' trong product -> k xoa' dc -> vui long` check lai.");
            return "redirect:/admin/category";
        }

        if (categoryOptional.isPresent()) {
            categoryService.deleteById(id);
            ra.addFlashAttribute(   "msg", "Delete Success");
            return "redirect:/admin/category";
        }

        ra.addFlashAttribute("err", "ID not found");
        return "redirect:/admin/category";
    }
}