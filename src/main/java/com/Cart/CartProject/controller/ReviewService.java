package com.Cart.CartProject.controller;

import com.Cart.CartProject.model.Review;
import com.Cart.CartProject.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepo;

    public void saveReview(Review review) {
        reviewRepo.save(review);
    }

    public List<Review> getAllReviews() {
        return reviewRepo.findAll();
    }
}