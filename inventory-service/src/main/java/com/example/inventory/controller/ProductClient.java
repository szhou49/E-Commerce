package com.example.inventory.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service") // The name should match the Product Service in Eureka
public interface ProductClient {

    // Check if a product exists by ID
    @GetMapping("/products/{id}/exists")
    boolean doesProductExist(@PathVariable Long id);
}
