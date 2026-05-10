// com.Cart.CartProject.controller.CartController.java

package com.Cart.CartProject.controller;

import com.Cart.CartProject.model.Post;
import com.Cart.CartProject.model.CartItem;
import com.Cart.CartProject.repository.CartItemRepository;
import com.Cart.CartProject.repository.ProductService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
public class CartController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CartItemData cartItemData;
    @Autowired
    private CartItemRepository cartItemRepository;


    // @GetMapping("/cart/fruit/{id}")
    // public String addToCart(@PathVariable int id) {
    //     Post product = productService.findById(id).orElse(null);
    //     if (product != null) {
    //         cartItemData.addProductToCart(product);
    //     } 
    //     return "redirect:/data";
    // }
    @GetMapping("/cart/fruit/{id}")
    public String addToCart(@PathVariable int id, HttpSession session) {
        String email = (String) session.getAttribute("userEmail");
        if (email == null) {
            return "redirect:/login";
        }
        Post product = productService.findById(id).orElse(null);
        if (product != null) {
            cartItemData.addProductToCart(product, email); 
        }
        return "redirect:/data";
    }

    @PostMapping("/cart/increase/{id}")
    public String increaseQuantity(@PathVariable int id) {
        cartItemData.increaseQuantity(id);
        return "redirect:/cart/items";
    }

    @PostMapping("/cart/decrease/{id}")
    public String decreaseQuantity(@PathVariable int id) {
        cartItemData.decreaseQuantity(id);
        return "redirect:/cart/items";
    }

    @PostMapping("/cart/delete/{id}")
    public String deleteCartItem(@PathVariable int id) {
        cartItemData.deleteCartItem(id);
        return "redirect:/cart/items";
    }

    @GetMapping("/cart/items")
    public String viewCart(HttpSession session, Model model) {
        String email = (String) session.getAttribute("userEmail");
        if (email == null) {
            return "redirect:/login";
        }
        List<CartItem> cartItems = cartItemData.getAllItems(email);
        double total = cartItems.stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalPrice", total);
        return "cartitem";
    }

@GetMapping("/cart")
public String showCartData(@RequestParam(name = "sort", required = false) String sort, HttpSession session, Model model) {
    List<Post> cartItems;
    if ("lowToHigh".equals(sort) || "asc".equals(sort)) {
        cartItems = cartItemRepository.findAllByOrderByPriceAsc();
    } else if ("highToLow".equals(sort) || "desc".equals(sort)) {
        cartItems = cartItemRepository.findAllByOrderByPriceDesc();
    } else {
        cartItems = cartItemRepository.findAll();
    }

    String email = (String) session.getAttribute("userEmail");
    boolean isDeveloper = "admin@admin.com".equals(email);
    model.addAttribute("isDeveloper", isDeveloper);

    model.addAttribute("cartItems", cartItems);
    return "index"; 
}
}