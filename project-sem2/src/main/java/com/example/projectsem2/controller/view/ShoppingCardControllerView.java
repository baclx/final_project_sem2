package com.example.projectsem2.controller.view;

import com.example.projectsem2.model.Product;
import com.example.projectsem2.model.ShoppingCard;
import com.example.projectsem2.model.User;
import com.example.projectsem2.service.impl.UserServiceImplAdmin;
import com.example.projectsem2.service.implement.ProductServiceImpl;
import com.example.projectsem2.service.implement.ShoppingCardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ShoppingCardControllerView {
    @Autowired
    ShoppingCardServiceImpl shoppingCardService;
    @Autowired
    UserServiceImplAdmin userService;
    @Autowired
    ProductServiceImpl productService;
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
    @GetMapping("/shoppingcard/user/{id}")
    public String getShoppingCard(@PathVariable("id") Long id, Model model){
        List<ShoppingCard> shoppingCards = shoppingCardService.getAllShoppingCard(id);
        model.addAttribute("shoppingCards",shoppingCards);
        List<Long> shoppingCardIds = new ArrayList<>();
        shoppingCards.forEach(sc->{
            Long cardId = sc.getId();
            shoppingCardIds.add(cardId);
        });
        model.addAttribute("cardIds",shoppingCardIds);
        Integer total = shoppingCards.size();
        if(shoppingCards.size()!=0){
            model.addAttribute("total",total);
        }else{
            total = 0;
            model.addAttribute("total",total);
        }
        model.addAttribute("userId",getcurrentUserId());

        return "shoppingcard";
    }

    @PostMapping("/addShoppingCard")
    public String addShoppingCard(@ModelAttribute ShoppingCard shoppingCard){
        String productName = shoppingCard.getProductName();
        Product product = productService.findByName(productName);
        String image = product.getImage();
        shoppingCard.setProductImage(image);
        shoppingCard.setCardPrice(shoppingCard.getCardPrice());
        shoppingCardService.saveShoppingCard(shoppingCard);
        Long id = getcurrentUserId();
        return "redirect:/shoppingcard/user/"+id;
    }

}
