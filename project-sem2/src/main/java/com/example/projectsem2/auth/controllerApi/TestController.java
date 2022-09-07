package com.example.projectsem2.auth.controllerApi;

import com.example.projectsem2.model.Product;
import com.example.projectsem2.service.impl.ProductServiceImplAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    ProductServiceImplAdmin productService;

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/user")
//    @PreAuthorize("hasRole('ROLE_USER') or hasRole('USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public String userAccess() {
        return "User Content.";
    }

    @GetMapping("/mod")
//    @PreAuthorize("hasRole('MODERATOR')")
    public String moderatorAccess() {
        return "Moderator Board.";
    }

    @GetMapping("/admin")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }

    @GetMapping("/product")
    public ResponseEntity<List<Product>> index() {
        List<Product> products = productService.findAll();

        if(products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
