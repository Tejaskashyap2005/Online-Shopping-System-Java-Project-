package com.shopping;

import com.shopping.frontend.LoginFrame;

public class Main {
    public static void main(String[] args) {
        // Start the application by showing the login frame
        java.awt.EventQueue.invokeLater(() -> {
            new LoginFrame().setVisible(true);
        });
    }
} 