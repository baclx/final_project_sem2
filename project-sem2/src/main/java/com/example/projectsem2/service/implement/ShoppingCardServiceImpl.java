package com.example.projectsem2.service.implement;

import com.example.projectsem2.model.ShoppingCard;
import com.example.projectsem2.repository.ShoppingCardRepository;
import com.example.projectsem2.service.ShoppingCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;
import java.util.Objects;

@Service
public class ShoppingCardServiceImpl implements ShoppingCardService {
    @Autowired
    ShoppingCardRepository shoppingCardRepository;


    @Override
    public List<ShoppingCard> getAllShoppingCard(Long userId) {
        return shoppingCardRepository.getAllByUserId(userId);
    }


    @Override
    public void deleteShoppingCardById(Long id) {
        shoppingCardRepository.deleteById(id);
    }

    @Override
    public ShoppingCard saveShoppingCard(ShoppingCard shoppingCard) {
        List<ShoppingCard> shoppingCards = shoppingCardRepository.findAll();
        if (shoppingCards.size() != 0) {
            for (int i = 0; i <= shoppingCards.size(); i++) {
                if (shoppingCards.get(i).getProductName().equals(shoppingCard.getProductName())
                        && shoppingCards.get(i).getIce().equals(shoppingCard.getIce())
                        && shoppingCards.get(i).getSize().equals(shoppingCard.getSize())
                        && shoppingCards.get(i).getSugar().equals(shoppingCard.getSugar())
                        && shoppingCards.get(i).getTopping().equals(shoppingCard.getTopping())
                        && shoppingCards.get(i).getUserId().equals(shoppingCard.getUserId())) {

                    shoppingCards.get(i).setProductQuantity(shoppingCards.get(i).getProductQuantity() + 1);
                    shoppingCards.get(i).setId(shoppingCards.get(i).getId());
                    shoppingCardRepository.save(shoppingCards.get(i));
                    return shoppingCards.get(i);
                }
                else if(i==shoppingCards.size()-1 &&
                        !Objects.equals(shoppingCards.get(i).getProductName(), shoppingCard.getProductName())
                        || !Objects.equals(shoppingCards.get(i).getIce(), shoppingCard.getIce())
                        || !Objects.equals(shoppingCards.get(i).getSize(), shoppingCard.getSize())
                        || !Objects.equals(shoppingCards.get(i).getSugar(), shoppingCard.getSugar())
                        || !Objects.equals(shoppingCards.get(i).getTopping(), shoppingCard.getTopping())
                        || !Objects.equals(shoppingCards.get(i).getUserId(), shoppingCard.getUserId())){
                        return shoppingCardRepository.save(shoppingCard);
                }
            }
        }else if(shoppingCards.size() == 0) {
            return shoppingCardRepository.save(shoppingCard);
        }
        return shoppingCardRepository.save(shoppingCard);
    }

    @Override
    public ShoppingCard updateShoppingCard(ShoppingCard shoppingCard, Long id){
        ShoppingCard shoppingCard1 = shoppingCardRepository.findById(id).get();
            shoppingCard1.setProductPrice(shoppingCard.getProductPrice());
            shoppingCard1.setProductQuantity(shoppingCard.getProductQuantity());
            shoppingCard1.setProductImage(shoppingCard.getProductImage());
            shoppingCard1.setIce(shoppingCard.getIce());
            shoppingCard1.setSize(shoppingCard.getSize());
            shoppingCard1.setSugar(shoppingCard.getSugar());
            shoppingCard1.setTopping(shoppingCard.getTopping());
            shoppingCard1.setUserId(shoppingCard.getUserId());
            shoppingCard1.setCardPrice(shoppingCard.getCardPrice());
            shoppingCard1.setId(id);
            shoppingCardRepository.save(shoppingCard1);
        return shoppingCard1;
    }


    public ShoppingCard findById(Long id){
        return shoppingCardRepository.findById(id).get();
    }

}
