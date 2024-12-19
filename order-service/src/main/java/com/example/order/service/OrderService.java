package com.example.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.order.controller.InventoryClient;
import com.example.order.controller.PaymentClient;
import com.example.order.model.Order;
import com.example.order.repository.OrderRepository;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private InventoryClient inventoryClient;

    @Autowired
    private PaymentClient paymentClient;

    public String placeOrder(Order order) {
        // Check stock availability from Inventory Service
        String stockResponse = inventoryClient.checkStock(order.getProductName(), order.getQuantity());
        if (!"In Stock".equals(stockResponse)) {
            return "Order Failed: Product is out of stock.";
        }

        // Process payment using Payment Service
        String paymentResponse = paymentClient.processPayment(order.getPrice() * order.getQuantity());
        if (!"Payment Successful".equals(paymentResponse)) {
            return "Order Failed: Payment processing failed.";
        }

        // Save order in database
        order.setStatus("PLACED");
        orderRepository.save(order);

        return "Order Placed Successfully!";
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
