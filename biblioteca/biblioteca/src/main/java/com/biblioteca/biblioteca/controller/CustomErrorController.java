package com.biblioteca.biblioteca.controller;


import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomErrorController implements ErrorController {

    // Handles errors and shows the error page
    @GetMapping("/error")
    public String handleError() {
        return "error"; // Muestra el template error.html
    }
}

