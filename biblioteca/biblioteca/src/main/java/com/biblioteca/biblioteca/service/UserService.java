package com.biblioteca.biblioteca.service;

import com.biblioteca.biblioteca.model.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    private final Map<String, User> userMap = new HashMap<>();
    private static int nextId = 1;

    // adds a new user and generates an ID
    public void createUser(User user) {
        String generatedId = String.valueOf(nextId++);
        user.setId(generatedId);
        userMap.put(generatedId, user);
    }

// returns a user by ID
    public User getUser(String id) {
        return userMap.get(id);
    }
// replaces an user
    public void replaceUser(String id, User newUser) {
        newUser.setId(id);
        userMap.put(id, newUser);
    }

    // updates the fields provided
    public void patchUser(String id, User partialUser) {
        User existingUser = userMap.get(id);
        if (existingUser != null) {
            if (partialUser.getName() != null && !partialUser.getName().isEmpty()) {
                existingUser.setName(partialUser.getName());
            }
            if (partialUser.getEmail() != null && !partialUser.getEmail().isEmpty()) {
                existingUser.setEmail(partialUser.getEmail());
            }
        }
    }

    // deletes an user by ID
    public void deleteUser(String id) {
        userMap.remove(id);
    }    
    // deletes all users
    public void deleteAllUsers() {
        userMap.clear();
    }
    // returns all users
    public Map<String, User> getAllUsers() {
        return userMap;
    }
}
