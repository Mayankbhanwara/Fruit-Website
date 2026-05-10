package com.Cart.CartProject.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.Cart.CartProject.model.User;

public interface UserRepo extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    Optional<User> findByResetToken(String resetToken);
}