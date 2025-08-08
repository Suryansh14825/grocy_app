package com.suryansh.groceryapp.Ui;

import com.suryansh.groceryapp.Model.Admin;
import com.suryansh.groceryapp.Model.Product;
import com.suryansh.groceryapp.Services.AdminService;
import com.suryansh.groceryapp.Services.ProductService;

import java.util.List;
import java.util.Scanner;

public class AdminMenu {
    private AdminService adminService = new AdminService();
    private ProductService productService = new ProductService();
    private Scanner scanner = new Scanner(System.in);

    public void start() {
        System.out.println("=== Admin Login ===");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        Admin admin = adminService.login(username, password);

        if (admin != null) {
            System.out.println("Login successful! Welcome Admin " + admin.getUsername());
            showAdminMenu();
        } else {
            System.out.println("Login failed! Invalid credentials.");
        }
    }

    private void showAdminMenu() {
        while (true) {
            System.out.println("1. Add Product");
            System.out.println("2. View All Products");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("5. Logout");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    viewAllProducts();
                    break;
                case 3:
                    updateProduct();
                    break;
                case 4:
                    deleteProduct();
                    break;
                case 5:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }

        }
    }

    private void addProduct() {
        System.out.print("Enter Product Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Product Price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter Available Quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // consume newline

        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setStockQuantity(quantity);

        boolean added = productService.addProduct(product);
        if (added) {
            System.out.println("Product added successfully!");
        } else {
            System.out.println("Failed to add product.");
        }
    }

    private void viewAllProducts() {
        List<Product> products = productService.getallProducts();
        System.out.println("\n=== Product List ===");
        for (Product p : products) {
            System.out.println("ID: " + p.getId() + " | Name: " + p.getName() + " | Price: ₹" + p.getPrice() + " | Qty: " + p.getStockQuantity());
        }
    }

    private void updateProduct() {
        System.out.print("Enter Product ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter New Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter New Price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter New Quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        Product updatedProduct = new Product();
        updatedProduct.setId(id);
        updatedProduct.setName(name);
        updatedProduct.setPrice(price);
        updatedProduct.setStockQuantity(quantity);

        boolean success = productService.updateProduct(updatedProduct);
        if (success) {
            System.out.println("Product updated successfully.");
        } else {
            System.out.println("Failed to update product.");
        }
    }

    private void deleteProduct() {
        System.out.print("Enter Product ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean success = productService.deleteProduct(id);
        if (success) {
            System.out.println("Product deleted successfully.");
        } else {
            System.out.println("Failed to delete product.");
        }
    }

}

