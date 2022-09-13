package com.example.projectsem2.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
public class Payment {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;

    @Basic
    @Column(name = "type")
    private String type;

    @OneToMany(mappedBy = "paymentByPaymentId")
    @JsonBackReference
    private Collection<Order> ordersById;
}
