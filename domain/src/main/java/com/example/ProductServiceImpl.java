package com.example;

import com.example.model.Product;
import com.example.ports.ProductPersistencePort;
import com.example.ports.ProductService;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
public class ProductServiceImpl implements ProductService {

    private final ProductPersistencePort productRepository;

    public ProductServiceImpl(ProductPersistencePort productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void addProduct(Product product) {
        productRepository.addProduct(product);
    }

    @Override
    public void removeProduct(Product product) {
        productRepository.removeProduct(product);
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.getProducts();
    }

    @Override
    public Product getProductById(Long productId) {
        System.out.println("adsada");
        System.out.println("adsada");
        System.out.println("adsada111");
        return productRepository.getProductById(productId);
    }
}
