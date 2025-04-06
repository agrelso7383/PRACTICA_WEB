package com.biblioteca.biblioteca.service;

import com.biblioteca.biblioteca.model.Book;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class BookService {

     // Adds a new book and generates a unique ID
    private final Map<String, Book> bookMap = new HashMap<>();
    private static int nextId = 1;
    
     // Adds a new book and generates a unique ID
    public void createBook(Book book) {
        String generatedId = String.valueOf(nextId++);
        book.setId(generatedId);
        bookMap.put(generatedId, book);
    }
// returns a book by the ID
    public Book getBook(String id) {
        return bookMap.get(id);
    }

// replaces a book with a new one with the same ID
    public void replaceBook(String id, Book book) {
        book.setId(id); // aseguramos que el ID coincida
        bookMap.put(id, book); // reemplaza completamente el libro
    }

// uodates fields of a book
    public void patchBook(String id, Book updates) {
        Book existingBook = bookMap.get(id);
        if (existingBook != null) {
            if (updates.getTitle() != null && !updates.getTitle().isEmpty()) {
                existingBook.setTitle(updates.getTitle());
            }
            if (updates.getAuthor() != null && !updates.getAuthor().isEmpty()) {
                existingBook.setAuthor(updates.getAuthor());
            }
            // ID no changed
        }
    }

// deletes a book by ID
    public void deleteBook(String id) {
        bookMap.remove(id);
    }
// deletes all books
    public void deleteAllBooks() {
        bookMap.clear(); 
    }
    
// returns all books
    public Map<String, Book> getAllBooks() {
        return bookMap;
    }
}
