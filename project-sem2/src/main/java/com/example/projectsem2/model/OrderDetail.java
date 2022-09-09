package com.example.projectsem2.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
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
    @CreationTimestamp
    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp createdAt;

    @Basic
    @UpdateTimestamp
    @Column(name = "updated_at")
    @UpdateTimestamp
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
