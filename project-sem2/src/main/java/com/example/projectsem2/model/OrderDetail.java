package com.example.projectsem2.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "order_detail", schema = "sb_project_sem2", catalog = "")
public class OrderDetail {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;

//    @Basic
//    @Column(name = "order_id")
//    private long orderId;
//
//    @Basic
//    @Column(name = "product_id")
//    private long productId;

    @Basic
    @Column(name = "quantity")
    private int quantity;

    @Basic
    @Column(name = "sale")
    private int sale;

    @Basic
    @Column(name = "price")
    private double price;

    @Basic
    @Column(name = "created_at")
    private Timestamp createdAt;

    @Basic
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
    private Order orderByOrderId;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    private Product productByProductId;


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
