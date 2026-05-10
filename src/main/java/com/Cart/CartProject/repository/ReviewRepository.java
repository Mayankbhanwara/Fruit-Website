package com.Cart.CartProject.repository;

import com.Cart.CartProject.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

void deleteById(Long id);
}