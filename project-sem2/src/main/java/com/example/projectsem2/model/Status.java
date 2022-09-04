package com.example.projectsem2.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Status {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;

    private String name;
}
