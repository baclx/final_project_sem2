package com.example.projectsem2.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Data
public class Product {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;

    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "image")
    private String image;

    @Basic
    @Column(name = "price")
    private double price;
    @Basic
    @Column(name = "sale")
    private short sale;
    @Basic
    @Column(name = "quantity")
    private int quantity;
    @Basic
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Basic
    @Column(name = "updated_at")
    private Timestamp updatedAt;
    @OneToMany(mappedBy = "productByProductId")
    private Collection<OrderDetail> orderDetailsById;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    private Category categoryById;

   

    @OneToMany(mappedBy = "productByProductId")
    private Collection<Review> reviewsById;

}
