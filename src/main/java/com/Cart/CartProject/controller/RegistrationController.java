package com.Cart.CartProject.controller;

import com.Cart.CartProject.model.CartUser;
import com.Cart.CartProject.model.Entry;
import com.Cart.CartProject.model.User;
import com.Cart.CartProject.repository.EntryRepository;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
public class RegistrationController {

    @Autowired
    private EntryRepository entryRepository;

@GetMapping("/Registration")
public String showForm(HttpSession session, Model model) {
    String email = (String) session.getAttribute("userEmail");

    Entry user = new Entry();  // Registration model
    user.setEmail(email);      // Pre-fill email if available

    model.addAttribute("user", user);
    return "registration";
}

@PostMapping("/Registration")
public String saveUser(@ModelAttribute Entry user, HttpSession session, Model model) {
    entryRepository.save(user);

    // Store registration data in session
    session.setAttribute("registeredUser", user);
    model.addAttribute("message", "Registration successful!");

    return "redirect:/data";
}

@GetMapping("/back")
public String homePage() {
    return "redirect:/data";
}
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
}