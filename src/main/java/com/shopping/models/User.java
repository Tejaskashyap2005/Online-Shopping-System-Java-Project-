package com.shopping.models;

public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private boolean isAdmin;

    public User(int id, String username, String password, String email, boolean isAdmin) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.isAdmin = isAdmin;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }
} 