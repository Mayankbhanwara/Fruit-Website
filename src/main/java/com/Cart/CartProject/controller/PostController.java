package com.Cart.CartProject.controller;

import java.util.List;
import jakarta.servlet.http.HttpSession;

// import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Cart.CartProject.model.CartItem;
import com.Cart.CartProject.model.Post;
import com.Cart.CartProject.model.Review;
import com.Cart.CartProject.repository.CartService;
import com.Cart.CartProject.repository.PostRepo;
import com.Cart.CartProject.repository.ProductService;

@Service
@Controller
public class PostController {

    @Autowired
    PostRepo repo;


     // Show all items
    @GetMapping({"/", "/data"})
    public String viewCartItems(HttpSession session, Model model) {
        String email = (String) session.getAttribute("userEmail");
        boolean isDeveloper = "admin@admin.com".equals(email);
        model.addAttribute("isDeveloper", isDeveloper);
        model.addAttribute("cartItems", repo.findAll());
        return "index"; // index.html
    }
    @GetMapping("/new")
    public String showNewCartItemForm(Model model) {
        model.addAttribute("post", new Post()); 
        return "Cart_form"; 
    }

    @PostMapping("/save")
    public String saveCartItem(@ModelAttribute Post post) {
        repo.save(post);
        return "redirect:/data";
    }
    // Show edit form
 @GetMapping("/cart/edit/{id}")
    public String editPost(@PathVariable int id, Model model){
        model.addAttribute("post", repo.findById(id)); 
        return "edit_cart";
    }

// Handle update submission
@PostMapping("/cart/update")
public String updateCartItem(@ModelAttribute("post") Post post) {
    repo.save(post); 
    return "redirect:/data";
}
@GetMapping("/cart/delete/{id}")
    public String deletePost(@PathVariable int id, Model model){
   repo.deleteById(id);
   return "redirect:/data";
}
}