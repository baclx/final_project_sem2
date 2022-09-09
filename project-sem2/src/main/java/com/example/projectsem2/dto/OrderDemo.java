package com.example.projectsem2.dto;

import lombok.Data;

@Data
public class OrderDemo {
    private Long userId;
    private int productQuantity;
    private String productName;
    private String size;
    private String sugar;
    private String ice;
    private String topping;
}
