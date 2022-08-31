package com.example.projectsem2.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Product {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;


    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "image")
    private String image;

    @Basic
    @Column(name = "price")
    private double price;

    @Basic
    @Column(name = "sale")
    private short sale;

    @Basic
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Basic
    @Column(name = "updated_at")
    private Timestamp updatedAt;
    @OneToMany(mappedBy = "productByProductId")
    @JsonIgnore
    private Collection<OrderDetail> orderDetailsById;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    private Category categoryById;

   

    @OneToMany(mappedBy = "productByProductId")
    private Collection<Review> reviewsById;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public short getSale() {
        return sale;
    }

    public void setSale(short sale) {
        this.sale = sale;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Collection<OrderDetail> getOrderDetailsById() {
        return orderDetailsById;
    }

    public void setOrderDetailsById(Collection<OrderDetail> orderDetailsById) {
        this.orderDetailsById = orderDetailsById;
    }

    public Category getCategoryById() {
        return categoryById;
    }

    public void setCategoryById(Category categoryById) {
        this.categoryById = categoryById;
    }

    public Collection<Review> getReviewsById() {
        return reviewsById;
    }

    public void setReviewsById(Collection<Review> reviewsById) {
        this.reviewsById = reviewsById;
    }
}
