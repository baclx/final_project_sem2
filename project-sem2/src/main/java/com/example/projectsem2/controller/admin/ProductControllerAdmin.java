package com.example.projectsem2.controller.admin;

import com.example.projectsem2.model.Category;
import com.example.projectsem2.model.Product;
import com.example.projectsem2.model.Sale;
import com.example.projectsem2.service.impl.CategoryServiceImplAdmin;
import com.example.projectsem2.service.impl.ProductServiceImplAdmin;
import com.example.projectsem2.service.impl.SaleServiceImplAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("/admin/product")
public class ProductControllerAdmin {
    private static final String UPLOAD_DIR = "src/main/resources/static/admin/images/product";
    @Autowired
    ProductServiceImplAdmin productService;

    @Autowired
    CategoryServiceImplAdmin categoryService;

    @Autowired
    SaleServiceImplAdmin saleService;

    @GetMapping("")
    public String index(
            Model model,
            @RequestParam(value = "sortField", defaultValue = "id") String sortField,
            @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir
    ) {
//        List<Product> productLists = productService.findAll();
        List<Product> sortPd = productService.sort(sortField, sortDir);

        model.addAttribute("productLists", sortPd);
        model.addAttribute("title", "Product");

        // sort
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);

        // reverse sortDir
        String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";
        model.addAttribute("reverseSortDir", reverseSortDir);

        return "admin/product/index";
    }

    @GetMapping("/add")
    public String add(
            Model model
    ) {
        List<Category> categoryLists = categoryService.findAll();
        List<Sale> saleLists = saleService.findAll();

        model.addAttribute("product", new Product());
        model.addAttribute("title", "Add Product");
        model.addAttribute("categoryLists", categoryLists);
        model.addAttribute("saleLists", saleLists);

        return "admin/product/create";
    }

    @PostMapping("/store")
    public String store(
            Product product,
            RedirectAttributes ra,
            @RequestParam(name = "_image") MultipartFile file
    ) {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        try {
            Path path = Paths.get(UPLOAD_DIR);

            product.setImage("/admin/images/product/" + fileName);

            Files.copy(file.getInputStream(), path.resolve(file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

        productService.save(product);

        ra.addFlashAttribute("msg", "Create Success");
        return "redirect:/admin/product";
    }

    @GetMapping("/delete/{id}")
    public String delete(
            @PathVariable("id") Long id,
            RedirectAttributes ra
    ) {
        Optional<Product> product = productService.findById(id);

        if (product.isPresent()) {
            productService.deleteById(id);
            ra.addFlashAttribute(   "msg", "Delete Success");
            return "redirect:/admin/product";
        }

        ra.addFlashAttribute(   "err", "ID not found");
        return "redirect:/admin/product";
    }
}