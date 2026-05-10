package com.Cart.CartProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

import com.Cart.CartProject.model.CartItem;
import com.Cart.CartProject.model.CartUser;
import com.Cart.CartProject.model.Entry; // ✅ Correct import
import com.Cart.CartProject.model.Post;
import com.Cart.CartProject.repository.CartService;
import com.Cart.CartProject.repository.CartService2;
import com.Cart.CartProject.repository.CartUserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class CartRegisController {

    @Autowired
    private CartService cartService2;

    @Autowired
    private CartUserRepository cartUserRepository;

    // ✅ Add product to cart
    public void addProductToCart(Post product) {
        CartItem item = new CartItem();
        item.setname(product.getName());
        item.setQuantity(1);
        item.setPrice(product.getPrice());
        cartService2.save(item);
    }

    // ✅ Cart operations
    public List<CartItem> getAllItems() {
        return cartService2.findAll();
    }

    public void increaseQuantity(int id) {
        CartItem item = cartService2.findById(id).orElse(null);
        if (item != null) {
            item.setQuantity(item.getQuantity() + 1);
            cartService2.save(item);
        }
    }

    public void decreaseQuantity(int id) {
        CartItem item = cartService2.findById(id).orElse(null);
        if (item != null && item.getQuantity() > 1) {
            item.setQuantity(item.getQuantity() - 1);
            cartService2.save(item);
        }
    }

    public void deleteById(int id) {
        cartService2.deleteById(id);
    }

    public CartItem findById(int id) {
        return cartService2.findById(id).orElse(null);
    }

    // ✅ Show cart registration form
    @GetMapping("/Cart-Regis")
    public String showCartForm(HttpSession session, Model model) {
        String email = (String) session.getAttribute("userEmail");
        if (email == null) {
            return "redirect:/login";
        }

        CartUser user = new CartUser();
        Entry registeredUser = (Entry) session.getAttribute("registeredUser");

        if (registeredUser != null) {
            user = convertEntryToCartUser(registeredUser);
        } else {
            user.setEmail(email);
        }

        model.addAttribute("user", user);

        // Load specific user's cart items
        List<CartItem> cartItems = cartService2.findByUserEmail(email);
        double totalPrice = cartItems.stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalPrice", totalPrice);

        return "Cart-Regis";
    }

    // ✅ Convert Entry to CartUser
    private CartUser convertEntryToCartUser(Entry entry) {
        CartUser user = new CartUser();
        user.setFirstName(entry.getFirstName());
        user.setLastName(entry.getLastName());
        user.setAddress(entry.getAddress());
        user.setPhone(entry.getPhone());
        user.setEmail(entry.getEmail());
        user.setCity(entry.getCity());
        return user;
    }

    // ✅ Save CartUser
    // @PostMapping("/Cart-Regis")
    // public String registerUser(@Validated @ModelAttribute("user") CartUser user, BindingResult result) {
    //     if (result.hasErrors()) {
    //         return "Cart-Regis";
    //     }
    //     cartUserRepository.save(user);
    //     return "redirect:/payment";
    // }
    @PostMapping("/Cart-Regis")
    public String registerUser(@Validated @ModelAttribute("user") CartUser user,
                               BindingResult result,
                               RedirectAttributes redirectAttributes,
                               HttpSession session) {
        if (result.hasErrors()) {
            return "Cart-Regis";
        }

        String email = (String) session.getAttribute("userEmail");
        if (email == null) {
            return "redirect:/login";
        }

        // Fetch user-specific cart items
        List<CartItem> cartItems = cartService2.findByUserEmail(email);
        StringBuilder orderDetailsBuilder = new StringBuilder();
        double total = 0;
        for (CartItem item : cartItems) {
            orderDetailsBuilder.append(item.getQuantity()).append("x ").append(item.getName())
                               .append(" (₹").append(item.getPrice()).append("), ");
            total += item.getPrice() * item.getQuantity();
        }
        user.setOrderDetails(orderDetailsBuilder.toString());
        user.setTotalAmount(total);

        cartUserRepository.save(user);

        // Clear the user's cart after checkout
        cartService2.deleteByUserEmail(email);

        // Pass values through redirect
        redirectAttributes.addFlashAttribute("toAddress", user.getAddress());

        return "redirect:/payment";
    }

    // ✅ Admin endpoints for Booked Users
    @GetMapping("/admin/booked-users")
    public String viewBookedUsers(HttpSession session, Model model) {
        String email = (String) session.getAttribute("userEmail");
        if (!"admin@admin.com".equals(email)) {
            return "redirect:/"; // Redirect non-admins to home
        }

        List<CartUser> bookedUsers = cartUserRepository.findAll();
        model.addAttribute("bookedUsers", bookedUsers);
        model.addAttribute("isDeveloper", true);

        return "booked_users";
    }

    @GetMapping("/admin/booked-users/delete/{id}")
    public String deleteBookedUser(@org.springframework.web.bind.annotation.PathVariable int id, HttpSession session) {
        String email = (String) session.getAttribute("userEmail");
        if ("admin@admin.com".equals(email)) {
            cartUserRepository.deleteById(id);
        }
        return "redirect:/admin/booked-users";
    }
}