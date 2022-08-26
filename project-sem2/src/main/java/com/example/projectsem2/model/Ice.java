package com.example.projectsem2.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
@Entity
@Data
public class Ice {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "percent")
    private char percent;
    @OneToMany(mappedBy = "iceByIceId")
    private Collection<Product> productById;
}

