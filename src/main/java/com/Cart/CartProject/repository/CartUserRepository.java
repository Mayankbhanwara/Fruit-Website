package com.Cart.CartProject.repository;

import java.security.KeyStore.Entry;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Cart.CartProject.model.CartUser;

public interface CartUserRepository extends JpaRepository<CartUser, Integer> {
    
}