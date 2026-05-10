package com.Cart.CartProject.controller;

import com.Cart.CartProject.model.Review;
import com.Cart.CartProject.repository.ReviewRepository;

import jakarta.servlet.http.HttpSession;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

    // @GetMapping("/reviews")
    // public String showReviewForm(Model model) {
    //     model.addAttribute("review", new Review());
    //     model.addAttribute("reviews", reviewRepo.findAll());
    //     return "review"; // Maps to review.html
    // }@Controller
public class ReviewController {

    // @Autowired
    // private ReviewRepository reviewRepo;

    // GET: Show form with user's email pre-filled
    // @GetMapping("/reviews")
    // public String showReviewForm(HttpSession session, Model model) {
    //     String email = (String) session.getAttribute("userEmail");

    //     Review review = new Review();
    //     review.setEmail(email);  // Pre-fill email from session

    //     model.addAttribute("review", review);
    //     model.addAttribute("reviews", reviewRepo.findAll());  // Show all reviews
    //     return "review";
    // }

    // POST: Save review
    // @PostMapping("/reviews")
    // public String submitReview(@ModelAttribute("review") Review review) {
    //     reviewRepo.save(review);
    //     return "redirect:/reviews"; // Reload page to see new review
    // }

    // // Optional: Delete review
    // @PostMapping("/reviews/delete/{id}")
    // public String deleteReview(@PathVariable Long id) {
    //     reviewRepo.deleteById(id);
    //     return "redirect:/reviews";
    // }
}