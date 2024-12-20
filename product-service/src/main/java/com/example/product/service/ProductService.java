package com.example.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.product.model.Product;
import com.example.product.repository.ProductRepository;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product with ID " + id + " does not exist.");
        }
        return productRepository.findById(id).get();
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProductById(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product with ID " + id + " does not exist.");
        }
        productRepository.deleteById(id);
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    existingProduct.setName(updatedProduct.getName());
                    existingProduct.setDescription(updatedProduct.getDescription());
                    existingProduct.setPrice(updatedProduct.getPrice());
                    return productRepository.save(existingProduct);
                })
                .orElseThrow(() -> new RuntimeException("Product with ID " + id + " does not exist."));
    }

    public boolean productExistsById(Long id) {
        return productRepository.existsById(id);
    }
}
