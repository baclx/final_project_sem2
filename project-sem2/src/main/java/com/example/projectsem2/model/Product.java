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
    private Category categoryByCategoryId;
    @ManyToOne
    @JoinColumn(name = "brand_id", referencedColumnName = "id", nullable = false)
    private Brand brandByBrandId;
    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id", nullable = false)
    private Status statusByStatusId;
    @OneToMany(mappedBy = "productByProductId")
    private Collection<Review> reviewsById;
    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    private ProductType typeByTypeId;
    @ManyToOne
    @JoinColumn(name = "size_id", referencedColumnName = "id")
    private ProductSize sizeBySizeId;
    @ManyToOne
    @JoinColumn(name = "sugar_id", referencedColumnName = "id")
    private Sugar sugarBySugarId;
    @ManyToOne
    @JoinColumn(name="ice_id",referencedColumnName = "id" )
    private Ice iceByIceId;
    @ManyToOne
    @JoinColumn(name="topping_id",referencedColumnName = "id" )
    private Topping toppingByToppingId;
}
