package com.example.projectsem2.controller.view;

import com.example.projectsem2.model.*;
import com.example.projectsem2.repository.*;
import com.example.projectsem2.service.impl.UserServiceImplAdmin;
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
public class ProductControllerView {
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

    @Autowired
    CategoryRepository categoryRepository;

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



    @GetMapping("/menu")
    public String getMenus(Model model){
        Long id = getcurrentUserId();
        model.addAttribute("currentUserId",id);
        User currentUser = userService.getUserById(id).getBody();
        model.addAttribute("currentUser",currentUser);
       List<Category> categories = categoryRepository.findAll();
       model.addAttribute("categories",categories);
        List<Topping> toppings = toppingRepository.findAll();
        List<Ice> ices = iceRepository.findAll();
        List<Sugar> sugars = sugarRepository.findAll();
        List<ProductSize> sizes = productSizeRepository.findAll();
        model.addAttribute("toppings",toppings);
        model.addAttribute("ices",ices);
        model.addAttribute("sugars",sugars);
        model.addAttribute("sizes",sizes);
        return "menu";
    }

}
