package com.suryansh.groceryapp.Repository;

import com.suryansh.groceryapp.Model.Admin;

import java.sql.*;

public class AdminRepository {
    private static final String URL = "jdbc:mysql://localhost:3306/grocerydb";
    private static final String USER = "root";
    private static final String PASSWORD = "****";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public Admin login(String username, String password) {
        String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Admin admin = new Admin();
                admin.setId(rs.getInt("id"));
                admin.setUsername(rs.getString("username"));
                admin.setPassword(rs.getString("password"));
                return admin;
            }

        } catch (SQLException e) {
            System.out.println("Admin login failed: " + e.getMessage());
        }

        return null;
    }
}

