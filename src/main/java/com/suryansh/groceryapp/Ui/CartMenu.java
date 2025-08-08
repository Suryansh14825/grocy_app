package com.suryansh.groceryapp.Ui;

import com.suryansh.groceryapp.Model.CartItem;
import com.suryansh.groceryapp.Model.Customer;
import com.suryansh.groceryapp.Services.CartService;

import java.util.List;
import java.util.Scanner;

public class CartMenu {

    private CartService cartService = new CartService();
    private Scanner scanner = new Scanner(System.in);

    public void start(Customer customer) {
        while (true) {
            System.out.println("\n=== Cart Menu ===");
            System.out.println("1. Add to Cart");
            System.out.println("2. View Cart");
            System.out.println("3. Update Item Quantity");
            System.out.println("4. Remove Item");
            System.out.println("5. Clear Cart");
            System.out.println("6. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addToCart(customer);
                    break;
                case 2:
                    viewCart(customer);
                    break;
                case 3:
                    updateItemQuantity(customer);
                    break;
                case 4:
                    removeItem(customer);
                    break;
                case 5:
                    clearCart(customer);
                    break;
                case 6:
                    return; // back to main menu
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void addToCart(Customer customer) {
        System.out.print("Enter Product ID: ");
        int productId = scanner.nextInt();
        System.out.print("Enter Quantity: ");
        int quantity = scanner.nextInt();

        CartItem item = new CartItem();
        item.setCustomerId(customer.getId());
        item.setProductId(productId);
        item.setQuantity(quantity);

        boolean success = cartService.addToCart(item);
        if (success) {
            System.out.println("Item added to cart.");
        } else {
            System.out.println("Failed to add item.");
        }
    }

    private void viewCart(Customer customer) {
        List<CartItem> items = cartService.getCartItemsByCustomerId(customer.getId());

        if (items.isEmpty()) {
            System.out.println("Cart is empty.");
        } else {
            System.out.println("Your Cart Items:");
            for (CartItem item : items) {
                System.out.println("Product ID: " + item.getProductId() +
                        ", Quantity: " + item.getQuantity());
            }
        }
    }

    private void updateItemQuantity(Customer customer) {
        System.out.print("Enter Product ID to update: ");
        int productId = scanner.nextInt();
        System.out.print("Enter new quantity: ");
        int quantity = scanner.nextInt();

        boolean success = cartService.updateCartItemQuantity(customer.getId(), productId, quantity);
        if (success) {
            System.out.println("Cart item updated.");
        } else {
            System.out.println("Failed to update item.");
        }
    }

    private void removeItem(Customer customer) {
        System.out.print("Enter Product ID to remove: ");
        int productId = scanner.nextInt();

        boolean success = cartService.removeCartItem(customer.getId(), productId);
        if (success) {
            System.out.println("Item removed from cart.");
        } else {
            System.out.println("Failed to remove item.");
        }
    }

    private void clearCart(Customer customer) {
        boolean success = cartService.clearCart(customer.getId());
        if (success) {
            System.out.println("Cart cleared.");
        } else {
            System.out.println("Failed to clear cart.");
        }
    }
}

