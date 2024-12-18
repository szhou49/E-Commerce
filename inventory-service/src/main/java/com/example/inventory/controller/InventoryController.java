package com.example.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.inventory.service.InventoryService;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/check")
    public ResponseEntity<?> checkStock(@RequestParam String productName, @RequestParam int quantity) {
        boolean inStock = inventoryService.checkStock(productName, quantity);
        return ResponseEntity.ok(inStock ? "In Stock" : "Out of Stock");
    }
}
