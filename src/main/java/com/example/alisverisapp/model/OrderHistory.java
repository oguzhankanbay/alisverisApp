package com.example.alisverisapp.model;

import jakarta.persistence.*;
import java.util.Map;

@Entity
public class OrderHistory extends BaseModel {

    @ManyToOne
    private Order order;

    @ElementCollection
    private Map<String, Double> productPrices;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Map<String, Double> getProductPrices() {
        return productPrices;
    }

    public void setProductPrices(Map<String, Double> productPrices) {
        this.productPrices = productPrices;
    }
}
