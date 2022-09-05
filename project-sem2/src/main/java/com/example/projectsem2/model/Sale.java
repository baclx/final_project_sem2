package com.example.projectsem2.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@Table(name = "sale", schema = "sb_project_sem2")

public class Sale {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "percent")
    private String percent;

    @OneToMany(mappedBy = "sale")
    private Collection<Product> product;


}
