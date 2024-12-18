package com.example.inventory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.inventory.model.Inventory;
import com.example.inventory.repository.InventoryRepository;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    public boolean checkStock(String productName, int quantity) {
        Inventory inventory = inventoryRepository.findByProductName(productName);
        return inventory != null && inventory.getStock() >= quantity;
    }

    public void deductStock(String productName, int quantity) {
        Inventory inventory = inventoryRepository.findByProductName(productName);
        if (inventory != null && inventory.getStock() >= quantity) {
            inventory.setStock(inventory.getStock() - quantity);
            inventoryRepository.save(inventory);
        }
    }
}
