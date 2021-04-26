package com.example.adapters;

import com.example.entities.ProductEntity;
import com.example.mapper.ProductProductEntityMapper;
import com.example.model.Product;
import com.example.ports.ProductPersistencePort;
import com.example.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class ProductPersistenceAdapter implements ProductPersistencePort{

    private final ProductRepository productRepository;

    private final ProductProductEntityMapper mapper;

    @Override
    public void addProduct(Product product) {
        productRepository.save(mapper.toProductEntity(product));
    }

    @Override
    public void removeProduct(Product product) {
        productRepository.delete(mapper.toProductEntity(product));
    }

    @Override
    public List<Product> getProducts() {
        List<ProductEntity> products = productRepository.findAll();
        List<Product> response = new ArrayList<>(products.size());
        for (ProductEntity product: products) {
            response.add(mapper.toProduct(product));
        }
        return response;
    }

    @Override
    public Product getProductById(Long productId) {
        return mapper.toProduct(productRepository.findById(productId).orElse(null));
    }
}
