package com.Cart.CartProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CartController2 {
      @Autowired
    private CartItemData cartItemData;
    
    @PostMapping("/Cart-Regis/increase/{id}")
public String increaseQuantity(@PathVariable int id) {
    cartItemData.increaseQuantity(id);
    return "redirect:/Cart-Regis";
}

@PostMapping("/Cart-Regis/decrease/{id}")
public String decreaseQuantity(@PathVariable int id) {
    cartItemData.decreaseQuantity(id);
    return "redirect:/Cart-Regis";
}

@PostMapping("/Cart-Regis/delete/{id}")
public String deleteCartItem(@PathVariable int id) {
    cartItemData.deleteCartItem(id);
    return "redirect:/Cart-Regis";
}
}
