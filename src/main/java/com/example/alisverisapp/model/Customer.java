package com.example.alisverisapp.model;

import jakarta.persistence.Entity;

@Entity
public class Customer extends BaseModel {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Customer() {}
    public Customer(String name) {
        this.name = name;
    }
}
