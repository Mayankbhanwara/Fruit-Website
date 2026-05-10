package com.Cart.CartProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Cart.CartProject.model.CartItem;
import com.Cart.CartProject.model.Post;

public interface PostRepo extends JpaRepository<Post,Integer>{

    
}
