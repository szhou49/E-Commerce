package com.example.inventory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.inventory.model.Inventory;
import com.example.inventory.service.InventoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/inventory")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @GetMapping()
    public List<Inventory> getAllInventories() {
        return inventoryService.getAllInventories();
    }
    
    @GetMapping("/check")
    public ResponseEntity<?> checkStock(@RequestParam Long productId, @RequestParam int quantity) {
        try {
            boolean inStock = inventoryService.checkStock(productId, quantity);
            return ResponseEntity.ok(inStock ? "In Stock" : "Out of Stock");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Add stock for a product
    @PostMapping("/add")
    public ResponseEntity<?> addStock(@RequestParam Long productId, @RequestParam int quantity) {
        try {
            Inventory inventory = inventoryService.addStock(productId, quantity);
            return ResponseEntity.ok(inventory);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Deduct stock by productId
    @PostMapping("/deduct")
    public ResponseEntity<String> deductStock(@RequestParam Long productId, @RequestParam int quantity) {
        try {
            inventoryService.deductStock(productId, quantity);
            return ResponseEntity.ok("Stock deducted successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
