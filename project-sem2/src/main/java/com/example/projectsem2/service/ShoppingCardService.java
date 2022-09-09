package com.example.projectsem2.service;

import com.example.projectsem2.model.ShoppingCard;

import java.util.List;

public interface ShoppingCardService {
    List<ShoppingCard> getAllShoppingCard(Long userId);



    void deleteShoppingCardById(Long id);

    ShoppingCard saveShoppingCard(ShoppingCard shoppingCard);

    ShoppingCard updateShoppingCard(ShoppingCard shoppingCard, Long id);

    ;
}
