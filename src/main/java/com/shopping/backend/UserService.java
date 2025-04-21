package com.shopping.backend;

import com.shopping.models.User;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private List<User> users;
    private static UserService instance;

    private UserService() {
        users = new ArrayList<>();
        // Add some test users
        users.add(new User(1, "admin", "admin123", "admin@shopping.com", true));
        users.add(new User(2, "user", "user123", "user@shopping.com", false));
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public User authenticate(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public boolean registerUser(String username, String password, String email) {
        // Check if username already exists
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return false;
            }
        }

        // Create new user
        int newId = users.size() + 1;
        User newUser = new User(newId, username, password, email, false);
        users.add(newUser);
        return true;
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }
} 