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


// Creates books from the list sent
    @PostMapping 
    public void createBooks(@RequestBody List<Book> books) {
    for (Book book : books) 
        bookService.createBook(book);
    }
// Returns a book using its ID
    @GetMapping("/{id}")
    public Book getBook(@PathVariable String id) {
        return bookService.getBook(id);
    }
// Returns all books with the ID as the key
    @GetMapping
    public Map<String, Book> getAllBooks() {
        return bookService.getAllBooks();
    }
// Replaces a book completely
    @PutMapping("/{id}")
    public void updateBook(@PathVariable String id, @RequestBody Book book) {
        bookService.replaceBook(id, book); 
    }
// Replaces only some fields of the book
     @PatchMapping("/{id}")
    public void patchBook(@PathVariable String id, @RequestBody Book book) {
        bookService.patchBook(id, book); 
    }
// Deletes a book by the ID
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable String id) {
        bookService.deleteBook(id);
    }
// Deletes all books
    @DeleteMapping("/deleteAll")
    public void deleteAllBooks() {
        bookService.deleteAllBooks();

    }

    
}


