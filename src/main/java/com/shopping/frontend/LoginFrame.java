package com.shopping.frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.shopping.backend.UserService;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    private UserService userService;

    public LoginFrame() {
        setTitle("Shopping System - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        userService = UserService.getInstance();

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Username
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(new JLabel("Username:"), gbc);

        gbc.gridx = 1;
        usernameField = new JTextField(20);
        mainPanel.add(usernameField, gbc);

        // Password
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        passwordField = new JPasswordField(20);
        mainPanel.add(passwordField, gbc);

        // Buttons
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.CENTER;

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");
        
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);
        
        mainPanel.add(buttonPanel, gbc);

        // Add action listeners
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                
                if (userService.authenticate(username, password) != null) {
                    dispose(); // Close login window
                    new ShoppingFrame().setVisible(true); // Open shopping window
                } else {
                    JOptionPane.showMessageDialog(LoginFrame.this, 
                        "Invalid username or password!", "Login Failed",
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                
                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(LoginFrame.this, 
                        "Please enter both username and password!", "Registration Failed",
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (userService.registerUser(username, password, "")) {
                    JOptionPane.showMessageDialog(LoginFrame.this, 
                        "Registration successful! You can now login.", "Registration Success",
                        JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(LoginFrame.this, 
                        "Username already exists!", "Registration Failed",
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        add(mainPanel);
    }
} 