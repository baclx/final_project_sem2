package com.example.projectsem2.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Data
@Table(name = "Orders")
public class Order {
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
    @JoinColumn(name = "user_id")
    private User userByUserId;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status statusByStatusId;

    @OneToMany(mappedBy = "orderByOrderId")
    private Collection<OrderDetail> orderDetailsById;
}
