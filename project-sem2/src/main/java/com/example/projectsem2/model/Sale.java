package com.example.projectsem2.model;

import lombok.Data;

import javax.persistence.*;

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

    @OneToOne(mappedBy = "sale",fetch = FetchType.LAZY)
    private Product product;


}
