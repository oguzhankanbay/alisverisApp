package com.example.alisverisapp.service;

import com.example.alisverisapp.model.Order;
import com.example.alisverisapp.repository.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;

public class OrderService {
    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {}

    public Order getOrderForCode(Long Id) {
        return orderRepository.findById(Id).orElseThrow(() -> new RuntimeException("Sipariş Bulunamadı"));
    }

    public List<Order> getAllOrdersForCustomer(Long customerId) {
        return orderRepository.findAll().stream()
                .filter(order -> order.getCustomer().getId().equals(customerId))
                .collect(Collectors.toList());
    }




}
