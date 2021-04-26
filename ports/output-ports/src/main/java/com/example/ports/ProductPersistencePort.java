package com.example.ports;

import com.example.model.Product;

import java.util.List;

public interface ProductPersistencePort {
    void addProduct(Product product);

    void removeProduct(Product product);

    List<Product> getProducts();

    Product getProductById(Long productId);
}
