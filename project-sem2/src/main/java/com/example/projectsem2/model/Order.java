package com.example.projectsem2.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;


    @Basic
    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp createdAt;

    @Basic
    @UpdateTimestamp
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
