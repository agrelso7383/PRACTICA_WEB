package com.biblioteca.biblioteca.controller;

import com.biblioteca.biblioteca.model.Book;
import com.biblioteca.biblioteca.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

// Handles all the requests related to books
@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping 
    public void createBooks(@RequestBody List<Book> books) {
    for (Book book : books) 
        bookService.createBook(book);
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable String id) {
        return bookService.getBook(id);
    }

    @GetMapping
    public Map<String, Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PutMapping("/{id}")
    public void updateBook(@PathVariable String id, @RequestBody Book book) {
        bookService.replaceBook(id, book); 
    }

     @PatchMapping("/{id}")
    public void patchBook(@PathVariable String id, @RequestBody Book book) {
        bookService.patchBook(id, book); 
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable String id) {
        bookService.deleteBook(id);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAllBooks() {
        bookService.deleteAllBooks();

    }

    
}


