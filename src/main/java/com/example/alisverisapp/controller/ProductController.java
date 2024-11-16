package com.example.alisverisapp.controller;

import com.example.alisverisapp.model.Product;
import com.example.alisverisapp.service.ProductService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Resource
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public Product createProduct(@RequestParam String name, @RequestParam double price, @RequestParam int stock) {
        return productService.createProduct(name, price, stock);
    }

    @GetMapping
    public Product getProduct(@PathVariable Long id) {
        return productService.getProduct(id);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestParam String name, @RequestParam double price, @RequestParam int stock) {
        return productService.updateProduct(id, name, price, stock);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
