package com.biblioteca.biblioteca.model;

// This class represents a user of the library system
public class User {
    private String id;
    private String name;
    private String email;

    // Constructor
    public User() {}

    public User(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // Getters and Setters (user ID)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
// user's name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
// user's email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
