package com.example.inventory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.inventory.controller.ProductClient;
import com.example.inventory.model.Inventory;
import com.example.inventory.repository.InventoryRepository;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private ProductClient productClient
;
    public boolean checkStock(Long productId, int quantity) {
        validateProductId(productId);
        Inventory inventory = inventoryRepository.findByProductId(productId);
        return inventory != null && inventory.getStock() >= quantity;
    }

    public List<Inventory> getAllInventories() {
        return inventoryRepository.findAll();
    }

    public void deductStock(Long productId, int quantity) {
        validateProductId(productId);
        Inventory inventory = inventoryRepository.findByProductId(productId);
        if (inventory == null) {
            throw new RuntimeException("Product with ID " + productId + " does not exist in inventory.");
        }
        if (quantity <= 0) {
            throw new RuntimeException("Stock deduction must be greater than zero.");
        }
        if (inventory.getStock() < quantity) {
            throw new RuntimeException("Insufficient stock for product ID " + productId + ".");
        }
        inventory.setStock(inventory.getStock() - quantity);
        inventoryRepository.save(inventory);
    }

    public Inventory addStock(Long productId, int quantity) {
        validateProductId(productId);
        Inventory inventory = inventoryRepository.findByProductId(productId);
        if (quantity <= 0) {
            throw new RuntimeException("Stock addition must be greater than zero.");
        }
        if (inventory == null) {
            inventory = new Inventory();
            inventory.setProductId(productId);
        }
        inventory.setStock(inventory.getStock() + quantity);
        return inventoryRepository.save(inventory);
    }

    private void validateProductId(Long productId) {
        boolean exists = productClient.doesProductExist(productId);
        if (!exists) {
            throw new RuntimeException("Product with ID " + productId + " does not exist.");
        }
    }
}
