package com.biblioteca.biblioteca.service;

import com.biblioteca.biblioteca.model.User;
import com.biblioteca.biblioteca.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public User update(Long id, User newUser) {
        return userRepository.findById(id).map(user -> {
            user.setName(newUser.getName());
            user.setEmail(newUser.getEmail());
            return userRepository.save(user);
        }).orElseThrow();
    }

    public User patch(Long id, String name, String email) {
        return userRepository.findById(id).map(user -> {
            if (name != null) user.setName(name);
            if (email != null) user.setEmail(email);
            return userRepository.save(user);
        }).orElseThrow();
    }
}

