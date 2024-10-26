package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public User findByName(String name) {
        Optional<User> user = userRepository.findByName(name);
        return user.orElse(null);
    }

    public User findByNameAndPassword(String name, String password) {
        Optional<User> user = userRepository.findByNameAndPassword(name, password);
        return user.orElse(null);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }
}
