package com.biblioteca.biblioteca.service;

import com.biblioteca.biblioteca.model.Book;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class BookService {
    private final Map<String, Book> bookMap = new HashMap<>();
    private static int nextId = 1;

    public void createBook(Book book) {
        String generatedId = String.valueOf(nextId++);
        book.setId(generatedId);
        bookMap.put(generatedId, book);
    }

    public Book getBook(String id) {
        return bookMap.get(id);
    }

    
    public void replaceBook(String id, Book book) {
        book.setId(id); // aseguramos que el ID coincida
        bookMap.put(id, book); // reemplaza completamente el libro
    }


    public void patchBook(String id, Book updates) {
        Book existingBook = bookMap.get(id);
        if (existingBook != null) {
            if (updates.getTitle() != null && !updates.getTitle().isEmpty()) {
                existingBook.setTitle(updates.getTitle());
            }
            if (updates.getAuthor() != null && !updates.getAuthor().isEmpty()) {
                existingBook.setAuthor(updates.getAuthor());
            }
            // ID no se actualiza, se mantiene
        }
    }


    public void deleteBook(String id) {
        bookMap.remove(id);
    }

    public void deleteAllBooks() {
        bookMap.clear(); 
    }
    

    public Map<String, Book> getAllBooks() {
        return bookMap;
    }
}
