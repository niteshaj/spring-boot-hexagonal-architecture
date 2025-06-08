package com.example.controller;

import com.example.model.Product;
import com.example.ports.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products")
    List<Product> getProducts() {
        return productService.getProducts();
    }

    @PostMapping("/products")
    void addProduct(@RequestBody @Valid Product product) {
        productService.addProduct(product);
    }

    @DeleteMapping("/products")
    void removeProduct(@RequestBody @Valid Product product) {
        productService.removeProduct(product);
    }

    @GetMapping("/products/{productId}")
    Product getProductById(@PathVariable Long productId) {
        return productService.getProductById(productId);
    }
}
