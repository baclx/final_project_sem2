package com.example.projectsem2.controller.api;

import com.example.projectsem2.model.ShoppingCard;
import com.example.projectsem2.service.implement.ShoppingCardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shoppingcard")
public class ShoppingCardControllerApi {
    @Autowired
    ShoppingCardServiceImpl shoppingCardService;
    @PutMapping("/update/{id}")
    public ResponseEntity<ShoppingCard> updateShoppingCard(@PathVariable("id") long id, @RequestBody ShoppingCard shoppingCard){
      ShoppingCard card = shoppingCardService.updateShoppingCard(shoppingCard, id);
      return ResponseEntity.ok(card);
    }
}
