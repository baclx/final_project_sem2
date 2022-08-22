package com.example.projectsem2.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;

    @Basic
    @Column(name = "username")
    private String username;

    @Basic
    @Column(name = "email")
    private String email;

    @Basic
    @Column(name = "password")
    private String password;

    @Basic
    @Column(name = "phone")
    private String phone;

    @Basic
    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "userByUserId")
    private Collection<Order> ordersById;

    @OneToMany(mappedBy = "userByUserId")
    private Collection<Review> reviewsById;

    @OneToMany(mappedBy = "userByUserId")
    private Collection<UserRole> userRolesById;
}
