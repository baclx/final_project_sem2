package com.example.projectsem2.service.implement;

import com.example.projectsem2.model.ShoppingCard;
import com.example.projectsem2.repository.ShoppingCardRepository;
import com.example.projectsem2.service.ShoppingCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCardServiceImpl implements ShoppingCardService {
    @Autowired
    ShoppingCardRepository shoppingCardRepository;


    @Override
    public List<ShoppingCard> getAllShoppingCard(Long userId){
        return shoppingCardRepository.getAllByUserId(userId);
    }
    @Override
    public ShoppingCard addShoppingcard(ShoppingCard shoppingCard){
        return shoppingCardRepository.save(shoppingCard);
    }

    @Override
    public void deleteShoppingCardById(Long id){
        shoppingCardRepository.deleteById(id);
    }

}
