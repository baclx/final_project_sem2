package com.example.projectsem2.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
@Entity
@Getter
@Setter
public class Ice {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "percent")
    private String percent;
    @OneToMany(mappedBy = "iceByIceId")
    private Collection<OrderDetail> orderDetailsById;
}

