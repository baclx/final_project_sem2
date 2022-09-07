package com.example.projectsem2.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "shopping_card")
public class ShoppingCard {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "product_image")
    private String productImage;

    @Column(name = "product_price")
    private double productPrice;

    @Column(name = "product_quantity")
    private int productQuantity;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "size")
    private String size;

    @Column(name = "sugar")
    private String sugar;

    @Column(name = "ice")
    private String ice;

    @Column(name = "topping")
    private String topping;


}
