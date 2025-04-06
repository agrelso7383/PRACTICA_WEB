package com.biblioteca.biblioteca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// Handles web page navigation
@Controller
public class WebController {

   // Loads the home page
    @GetMapping("/")
    public String home() {
        return "home";  // home.html
    }

   // Loads the book list page
    @GetMapping("/bookList")
    public String books() {
        return "ListBooks";  // ListBooks.html
    }

// Loads the page for managing books (manageBooks.html)
    @GetMapping("/manageBooks")
    public String manageBooks() {
        return "manageBooks";  // manageBooks.html
    }

  // Loads the page for managing loans (manageLoans.html)
    @GetMapping("/manageLoans")
    public String manageLoans() {
        return "manageLoans";  // manageLoans.html
    }

// Loads the page for managing users (manageUsers.html)
    @GetMapping("/manageUsers")
    @GetMapping("/manageUsers")
    public String manageUsers() {
        return "manageUsers";  // manageUsers.html
    }

    // Loads the info page (info.html)
    @GetMapping("/info")
    public String info() {
        return "info";  // info.html
    }

   // Loads the About Us page (aboutUs.html)
    @GetMapping("/aboutUs")
    public String aboutUs() {
        return "aboutUs";  // aboutUs.html
    }

   
}
