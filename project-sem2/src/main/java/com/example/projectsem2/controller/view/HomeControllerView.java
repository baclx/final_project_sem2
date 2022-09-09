package com.example.projectsem2.controller.view;

import com.example.projectsem2.dto.OrderDemo;
import com.example.projectsem2.model.*;
import com.example.projectsem2.repository.IceRepository;
import com.example.projectsem2.repository.ProductSizeRepository;
import com.example.projectsem2.repository.SugarRepository;
import com.example.projectsem2.repository.ToppingRepository;
import com.example.projectsem2.service.impl.UserServiceImplAdmin;
import com.example.projectsem2.service.implement.ProductServiceImpl;
import com.example.projectsem2.service.implement.ShoppingCardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class HomeControllerView {
    @Autowired
    ProductServiceImpl productService;
    @Autowired
    UserServiceImplAdmin userService;
    @Autowired
    IceRepository iceRepository;
    @Autowired
    SugarRepository sugarRepository;
    @Autowired
    ProductSizeRepository productSizeRepository;
    @Autowired
    ToppingRepository toppingRepository;
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
    @GetMapping(value = {"/index","/home", "/"})
    public String showIndex(Model model){
        List<Product> topProduct = productService.getTopSeller();
        //Get top 4
        List<Product> top3Products = new ArrayList<>();
        for(int i = 0 ; i <= 2; i++){
            top3Products.add(topProduct.get(i));
        }
        model.addAttribute("products",top3Products);
        Long currentUserId = getcurrentUserId();
        if(currentUserId != 0){
            model.addAttribute("currentUserId",currentUserId);
            User currentUser = userService.findById(getcurrentUserId()).get();
            model.addAttribute("currentUser",currentUser);
            String username = currentUser.getUsername();
            model.addAttribute("username",username);
        }
        List<Topping> toppings = toppingRepository.findAll();
        List<Ice> ices = iceRepository.findAll();
        List<Sugar> sugars = sugarRepository.findAll();
        List<ProductSize> sizes = productSizeRepository.findAll();
        model.addAttribute("toppings",toppings);
        model.addAttribute("ices",ices);
        model.addAttribute("sugars",sugars);
        model.addAttribute("sizes",sizes);
        List<Product> listSale25 = productService.getAllSale25();
        List<Product> top3Sale25 = new ArrayList<>();
        for(int i = 0; i <= 2; i++){
            top3Sale25.add(listSale25.get(i));
        }
        model.addAttribute("sale25",top3Sale25);
        return "index";
    }


}
