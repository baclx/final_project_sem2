package com.example.projectsem2.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
public class ProductSize {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name")
    private char name;
    @OneToMany(mappedBy = "sizeBySizeId")
    private Collection<Product> productById;
}
