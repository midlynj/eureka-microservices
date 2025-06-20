package com.example.productservice.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;


@Entity
public class Product {
    public Product() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", couponCode='" + couponCode + '\'' +
                '}';
    }

    public Product(Long id, String name, String description, BigDecimal price, String couponCode) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.couponCode = couponCode;
    }

    private String description;
    private BigDecimal price;
    @Transient //ignore field when saved to database since proudct table dont have the column
    private String couponCode;
}
