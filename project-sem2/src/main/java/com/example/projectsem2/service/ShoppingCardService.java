package com.example.projectsem2.service;

import com.example.projectsem2.model.ShoppingCard;

import java.util.List;

public interface ShoppingCardService {
    List<ShoppingCard> getAllShoppingCard(Long userId);

    ShoppingCard addShoppingcard(ShoppingCard shoppingCard);

    void deleteShoppingCardById(Long id);
}
