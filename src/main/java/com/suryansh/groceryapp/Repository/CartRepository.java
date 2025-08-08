package com.suryansh.groceryapp.Repository;

import com.suryansh.groceryapp.Model.CartItem;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartRepository {
    private static final String URL = "jdbc:mysql://localhost:3306/grocerydb";
    private static final String USER="root";
    private static final String PASSWORD="******";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public boolean addToCart(CartItem item) {
        String sql = "INSERT INTO cart_items (customer_id, product_id, quantity) VALUES (?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, item.getCustomerId());
            stmt.setInt(2, item.getProductId());
            stmt.setInt(3, item.getQuantity());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error adding to cart: " + e.getMessage());
            return false;
        }
    }

    public List<CartItem> getCartItemsByCustomerId(int customerId) {
        List<CartItem> cartItems = new ArrayList<>();
        String sql = "SELECT * FROM cart_items WHERE customer_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                CartItem item = new CartItem();
                item.setId(rs.getInt("id"));
                item.setCustomerId(rs.getInt("customer_id"));
                item.setProductId(rs.getInt("product_id"));
                item.setQuantity(rs.getInt("quantity"));
                cartItems.add(item);
            }
        } catch(SQLException e){
            System.out.println("Error retrieving cart items: " + e.getMessage());
        }
        return cartItems;
    }

    public boolean updateCartItem(int customerId, int productId, int newQuantity) {
        String sql = "UPDATE cart_items SET quantity = ? WHERE customer_id = ? AND product_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, newQuantity);
            stmt.setInt(2, customerId);
            stmt.setInt(3, productId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error updating cart item: " + e.getMessage());
            return false;
        }
    }


    public boolean clearCart(int customerId) {
        String sql = "DELETE FROM cart_items WHERE customer_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error clearing cart: " + e.getMessage());
            return false;
        }
    }


    public boolean removeCartItem(int customerId, int productId) {
        String sql = "DELETE FROM cart_items WHERE customer_id = ? AND product_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            stmt.setInt(2, productId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error removing cart item: " + e.getMessage());
            return false;
        }
    }
    public CartItem getCartItem(int customerId, int productId) {
        String sql = "SELECT * FROM cart_items WHERE customer_id = ? AND product_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            stmt.setInt(2, productId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                CartItem item = new CartItem();
                item.setId(rs.getInt("id"));
                item.setCustomerId(rs.getInt("customer_id"));
                item.setProductId(rs.getInt("product_id"));
                item.setQuantity(rs.getInt("quantity"));
                return item;
            }
        } catch (SQLException e) {
            System.out.println("Error fetching cart item: " + e.getMessage());
        }
        return null;
    }


}


