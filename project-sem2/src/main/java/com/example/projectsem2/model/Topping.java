package com.example.projectsem2.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
@Entity
@Getter
@Setter
public class Topping {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "topping")
    private String topping;
    @OneToMany(mappedBy = "toppingByToppingId")
    private Collection<OrderDetail> orderDetailsById;
}

