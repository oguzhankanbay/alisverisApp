package com.example.alisverisapp.repository;

import com.example.alisverisapp.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {}