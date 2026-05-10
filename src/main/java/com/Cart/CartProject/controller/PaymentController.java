package com.Cart.CartProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.Random;

@Controller
public class PaymentController {

    private final List<String> fromAddresses = List.of(
        "123 MG Road, Bengaluru, Karnataka",
        "456 Sector 17, Chandigarh",
        "789 Ring Road, Delhi",
        "Plot 12, Gachibowli, Hyderabad",
        "Apt 304, Koregaon Park, Pune",
        "Block C, Salt Lake City, Kolkata",
        "No. 9, T Nagar, Chennai",
        "78 Civil Lines, Allahabad"
    );

    // @GetMapping("/payment")
    // public String showPaymentPage(Model model) {
    //     Random random = new Random();
    //     String randomFromAddress = fromAddresses.get(random.nextInt(fromAddresses.size()));
    //     model.addAttribute("randomFromAddress", randomFromAddress);
    //     return "paymentdetail";  // This is your Thymeleaf file: payment.html
    // }
    @GetMapping("/payment")
public String showPaymentPage(Model model, @ModelAttribute("toAddress") String toAddress) {
    // Add fromAddress
    Random random = new Random();
    String randomFromAddress = fromAddresses.get(random.nextInt(fromAddresses.size()));
    model.addAttribute("randomFromAddress", randomFromAddress);

    // toAddress is already added via flash attribute
    return "paymentdetail";
}

}