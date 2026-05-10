package com.Cart.CartProject.repository;

// import java.util.Optional;
import com.Cart.CartProject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface emailset extends JpaRepository<User, Integer> {
}