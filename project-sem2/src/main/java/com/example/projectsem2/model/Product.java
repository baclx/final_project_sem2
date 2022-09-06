package com.example.projectsem2.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Getter
@Setter
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="sale_id",referencedColumnName = "id")
    private Sale sale;

    @Basic
    @Column(name = "created_at")
    private Timestamp createdAt;

    @Basic
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @OneToMany(mappedBy = "productByProductId")
    @JsonIgnore
    private Collection<OrderDetail> orderDetailsById;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    private Category categoryById;

    @OneToMany(mappedBy = "productByProductId")
    @JsonIgnore
    private Collection<Review> reviewsById;

    public double getPriceAfterSale(double price, Sale sale){
        int sale_id = sale.getId();
        switch (sale_id){
            case 1:
                return price;
            case 2:
                return price - price*25/100;
            case 3:
                return price - price*50/100;
            case 4:
                return price-price*75/100;
        }
        return price;
    }
}
