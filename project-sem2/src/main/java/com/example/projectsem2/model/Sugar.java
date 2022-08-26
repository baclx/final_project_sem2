package com.example.projectsem2.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
@Entity
@Data
public class Sugar {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "percent")
    private char percent;
    @OneToMany(mappedBy = "sugarBySugarId")
    private Collection<Product> productById;
}
