package com.suryansh.groceryapp.Ui;

import com.suryansh.groceryapp.Model.Product;
import com.suryansh.groceryapp.Services.ProductService;

import java.util.List;
import java.util.Scanner;

public class ProductMenu {
    private ProductService productService = new ProductService();
    private Scanner scanner = new Scanner(System.in);

    public void showProductMenu() {
        while (true) {
            System.out.println("\n Product Menu ");
            System.out.println("1. View All Products");
            System.out.println("2. Go Back");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    viewAllProducts();
                    break;
                case 2:
                    return; // Go back to customer menu
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void viewAllProducts() {
        List<Product> productList = productService.getallProducts();
        if (productList.isEmpty()) {
            System.out.println("No products available.");
            return;
        }

        System.out.println("\nAvailable Products:");
        System.out.printf("%-5s %-20s %-10s %-10s\n", "ID", "Name", "Price", "Stock");
        for (Product product : productList) {
            System.out.printf("%-5d %-20s %-10.2f %-10d\n",
                    product.getId(),
                    product.getName(),
                    product.getPrice(),
                    product.getStockQuantity());
        }
    }
}

