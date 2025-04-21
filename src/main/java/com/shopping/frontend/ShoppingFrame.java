package com.shopping.frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.shopping.models.Product;
import java.util.ArrayList;
import java.util.List;

public class ShoppingFrame extends JFrame {
    private JPanel mainPanel;
    private JList<Product> productList;
    private DefaultListModel<Product> listModel;
    private JButton addToCartButton;
    private JButton viewCartButton;
    private JButton logoutButton;
    private List<Product> cart;

    public ShoppingFrame() {
        setTitle("Shopping System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        cart = new ArrayList<>();
        listModel = new DefaultListModel<>();
        initializeProducts();

        mainPanel = new JPanel(new BorderLayout());
        
        // Product List
        productList = new JList<>(listModel);
        productList.setCellRenderer(new ProductListRenderer());
        JScrollPane scrollPane = new JScrollPane(productList);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Buttons Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        addToCartButton = new JButton("Add to Cart");
        viewCartButton = new JButton("View Cart");
        logoutButton = new JButton("Logout");

        buttonPanel.add(addToCartButton);
        buttonPanel.add(viewCartButton);
        buttonPanel.add(logoutButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add action listeners
        addToCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Product selectedProduct = productList.getSelectedValue();
                if (selectedProduct != null) {
                    cart.add(selectedProduct);
                    JOptionPane.showMessageDialog(ShoppingFrame.this, 
                        "Added " + selectedProduct.getName() + " to cart!");
                } else {
                    JOptionPane.showMessageDialog(ShoppingFrame.this, 
                        "Please select a product first!");
                }
            }
        });

        viewCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCart();
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(ShoppingFrame.this,
                    "Are you sure you want to logout?", "Confirm Logout",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                
                if (response == JOptionPane.YES_OPTION) {
                    dispose();
                    new LoginFrame().setVisible(true);
                }
            }
        });

        add(mainPanel);
    }

    private void initializeProducts() {
        // Add some sample products
        listModel.addElement(new Product(1, "Laptop", "High-performance laptop", 999.99, 10, "Electronics"));
        listModel.addElement(new Product(2, "Smartphone", "Latest smartphone model", 699.99, 15, "Electronics"));
        listModel.addElement(new Product(3, "Headphones", "Wireless noise-cancelling headphones", 199.99, 20, "Electronics"));
        listModel.addElement(new Product(4, "T-shirt", "Cotton t-shirt", 19.99, 50, "Clothing"));
        listModel.addElement(new Product(5, "Jeans", "Classic blue jeans", 49.99, 30, "Clothing"));
    }

    private void showCart() {
        if (cart.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Your cart is empty!");
            return;
        }

        StringBuilder cartContent = new StringBuilder("Your Cart:\n\n");
        double total = 0.0;

        for (Product product : cart) {
            cartContent.append(product.getName())
                      .append(" - ₹")
                      .append(String.format("%.2f", product.getPrice()))
                      .append("\n");
            total += product.getPrice();
        }

        cartContent.append("\nTotal: ₹").append(String.format("%.2f", total));
        JOptionPane.showMessageDialog(this, cartContent.toString());
    }

    // Custom renderer for the product list
    private class ProductListRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof Product) {
                Product product = (Product) value;
                setText(String.format("%s - ₹%.2f (%s)", 
                    product.getName(), 
                    product.getPrice(),
                    product.getCategory()));
            }
            return this;
        }
    }
}
