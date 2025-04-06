package com.biblioteca.biblioteca.model;

// This class represents a book with its basic information
public class Book {
    private String id;
    private String title;
    private String author;

    // Constructor
    public Book() {}

    // Constructor with parameters to create a book
    public Book(String id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    // Getters and Setters (book ID)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
// book title
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
//book author
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
