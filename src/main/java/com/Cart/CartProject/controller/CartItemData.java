// com.Cart.CartProject.service.CartItemData.java

package com.Cart.CartProject.controller;

import com.Cart.CartProject.model.CartItem;
import com.Cart.CartProject.model.Post;
import com.Cart.CartProject.repository.CartService;

import jakarta.servlet.http.HttpSession;

import org.springframework.ui.Model; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@ControllerAdvice
@Controller
@Service
public class CartItemData {

    @Autowired
    private CartService cartService;

    public void addProductToCart(Post product, String email) {
        CartItem item = new CartItem();
        item.setname(product.getName());
        item.setQuantity(1);
        item.setPrice(product.getPrice());
        item.setUserEmail(email);
        cartService.save(item);
    }

    public List<CartItem> getAllItems(String email) {
        if (email == null) return List.of();
        return cartService.findByUserEmail(email);
    }

    public void increaseQuantity(int id) {
        CartItem item = cartService.findById(id).orElse(null);
        if (item != null) {
            item.setQuantity(item.getQuantity() + 1);
            cartService.save(item);
        }
    }

    public void decreaseQuantity(int id) {
        CartItem item = cartService.findById(id).orElse(null);
        if (item != null && item.getQuantity() > 1) {
            item.setQuantity(item.getQuantity() - 1);
            cartService.save(item);
        }
    }

    public void deleteCartItem(int id) {
        cartService.deleteById(id);
    }

    @ModelAttribute("cartCount")
    public int cartCount(HttpSession session) {
        String email = (String) session.getAttribute("userEmail");
        if (email == null) {
            return 0;
        }
        return cartService.countByUserEmail(email);
    }


}
