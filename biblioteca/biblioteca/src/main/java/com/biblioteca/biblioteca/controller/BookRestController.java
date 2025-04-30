package com.biblioteca.biblioteca.controller;

import com.biblioteca.biblioteca.model.Book;
import com.biblioteca.biblioteca.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookRestController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.findById(id).orElseThrow();
    }

    @PostMapping
    public List<Book> createBooks(@RequestBody List<Book> books) {
        return books.stream().map(bookService::save).toList();
    }


    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book book) {
        return bookService.update(id, book);
    }

    @PatchMapping("/{id}")
    public Book patchBook(@PathVariable Long id, @RequestBody Book book) {
        return bookService.patch(id, book.getTitle(), book.getAuthor());
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.delete(id);
    }
}
