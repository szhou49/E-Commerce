package com.example.product.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Product name is required.")
    @Size(max = 50, message = "Product name must not exceed 50 characters.")
    @Column(nullable = false, unique = true)
    private String name;

    @NotBlank(message = "Product description is required.")
    @Size(max = 255, message = "Product description must not exceed 255 characters.")
    @Column(nullable = false)
    private String description;

    @DecimalMin(value = "0.01", message = "Price must be greater than zero.")
    @Column(nullable = false)
    private Double price;

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
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
}
