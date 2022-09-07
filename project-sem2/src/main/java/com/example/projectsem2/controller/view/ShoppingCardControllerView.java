package com.example.projectsem2.controller.view;

import com.example.projectsem2.model.ShoppingCard;
import com.example.projectsem2.service.implement.ShoppingCardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;

@Controller
public class ShoppingCardControllerView {
    @Autowired
    ShoppingCardServiceImpl shoppingCardService;

    @GetMapping("/shoppingcard/user/{id}")
    public String getShoppingCard(@PathVariable("id") Long id, Model model){
        List<ShoppingCard> shoppingCards = shoppingCardService.getAllShoppingCard(id);
        model.addAttribute("shoppingCards",shoppingCards);
        Integer total = shoppingCards.size();
        if(shoppingCards.size()!=0){
            model.addAttribute("total",total);
        }else{
            total = 0;
            model.addAttribute("total",total);
        }

        return "shoppingcard";
    }

}
