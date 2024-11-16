package com.example.alisverisapp.service;

import com.example.alisverisapp.model.Product;
import com.example.alisverisapp.repository.ProductRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Resource
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(String name, double price, int stock) {
        return productRepository.save(new Product(name, price, stock));
    }

    public Product getProduct(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product updateProduct(Long productId, String name, double price, int stock) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product Yok!"));
        product.setName(name);
        product.setPrice(price);
        product.setStock(stock);
        return productRepository.save(product);
    }

    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }
}
