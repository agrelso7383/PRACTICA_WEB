package com.biblioteca.biblioteca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class WebController {

   
    @GetMapping("/")
    public String home() {
        return "home";  // home.html
    }

   
    @GetMapping("/bookList")
    public String books() {
        return "ListBooks";  // ListBooks.html
    }


    @GetMapping("/manageBooks")
    public String manageBooks() {
        return "manageBooks";  // manageBooks.html
    }

  
    @GetMapping("/manageLoans")
    public String manageLoans() {
        return "manageLoans";  // manageLoans.html
    }


    @GetMapping("/manageUsers")
    public String manageUsers() {
        return "manageUsers";  // manageUsers.html
    }

   
    @GetMapping("/info")
    public String info() {
        return "info";  // info.html
    }

 
    @GetMapping("/aboutUs")
    public String aboutUs() {
        return "aboutUs";  // aboutUs.html
    }

   
}
