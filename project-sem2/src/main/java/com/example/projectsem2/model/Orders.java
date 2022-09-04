package com.example.projectsem2.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Data
public class Orders {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;


    @Basic
    @Column(name = "created_at")
    private Timestamp createdAt;

    @Basic
    @Column(name = "updated_at")
    private Timestamp updatedAt;

//    @Basic
//    @Column(name = "user_id")
//    private long userId;

    @ManyToOne
    @JoinColumn(name = "payment_id")
    private Payment paymentByPaymentId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User userByUserId;

    @OneToMany(mappedBy = "orderByOrderId")
    private Collection<OrderDetail> orderDetailsById;
}
