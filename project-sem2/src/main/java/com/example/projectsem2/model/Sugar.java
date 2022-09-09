package com.example.projectsem2.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
@Entity
@Getter
@Setter
public class Sugar {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "percent")
    private String percent;
    @OneToMany(mappedBy = "sugarBySugarId")
    private Collection<OrderDetail> orderDetailsById;
}
