package com.example.alisverisapp.service;

import com.example.alisverisapp.model.Customer;
import com.example.alisverisapp.repository.CustomerRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer addCustomer(String name) {
        return customerRepository.save(new Customer(name));
    }
}

