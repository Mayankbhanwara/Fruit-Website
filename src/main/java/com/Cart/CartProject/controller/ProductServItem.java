package com.Cart.CartProject.controller;

import com.Cart.CartProject.model.Post;
import com.Cart.CartProject.repository.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServItem {

    @Autowired
    private PostRepo postRepo;

    public Optional<Post> getProductById(int id) {
        return postRepo.findById(id);
    }
}