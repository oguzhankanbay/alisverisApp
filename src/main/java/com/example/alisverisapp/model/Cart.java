package com.example.alisverisapp.model;

import jakarta.persistence.*;
import java.util.Map;

@Entity
public class Cart extends BaseModel {

    @OneToOne
    private Customer customer;

    @ElementCollection
    private Map<Product, Integer> products;

    private Double totalPrice;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<Product, Integer> products) {
        this.products = products;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
