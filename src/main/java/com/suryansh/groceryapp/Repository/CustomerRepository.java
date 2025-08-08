package com.suryansh.groceryapp.Repository;

import com.suryansh.groceryapp.Model.Customer;

import java.sql.*;

public class CustomerRepository {
    private static final String URL = "jdbc:mysql://localhost:3306/grocerydb";
    private static final String USER="root";
    private static final String PASSWORD="*****";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }



    public boolean registerCustomer(Customer customer){
        String sql="INSERT INTO customers (name,email,password,address,phone_number) VALUES (?,?,?,?,?)";
        try(Connection connection=getConnection();
            PreparedStatement stmt=connection.prepareStatement(sql)){
            stmt.setString(1,customer.getName());
            stmt.setString(2,customer.getEmail());
            stmt.setString(3, customer.getPassword());
            stmt.setString(4, customer.getAddress());
            stmt.setString(5, customer.getPhoneNumber());

            int rowsInserted= stmt.executeUpdate();
            return rowsInserted>0;
        }catch (SQLException e){
            System.out.println("Error Registring Customer: "+e.getMessage());
            return false;
        }
    }

    public Customer findbyemailandpassword(String email,String password){
        String query="SELECT * FROM customers WHERE email=? AND password=?";
        try(Connection connection=getConnection();
        PreparedStatement stmt=connection.prepareStatement(query)){
            stmt.setString(1,email);
            stmt.setString(2,password);

            ResultSet rs=stmt.executeQuery();

            if(rs.next()){
                Customer customer=new Customer();
                customer.setId(rs.getInt("id"));
                customer.setName(rs.getString("name"));
                customer.setEmail(rs.getString("email"));
                customer.setPhoneNumber(rs.getString("phone_number"));
                customer.setAddress(rs.getString("address"));
                customer.setPassword(rs.getString("password"));
                return customer;
            }
            }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public static boolean updateCustomer(Customer customer){
        String sql="UPDATE customers SET name=?,email=?,password=?,address=?,phone_number=? WHERE id=?";
        try(Connection connection=getConnection();
        PreparedStatement stmt=connection.prepareStatement(sql)){
            stmt.setString(1,customer.getName());
            stmt.setString(2,customer.getEmail());
            stmt.setString(3,customer.getPassword());
            stmt.setString(4,customer.getAddress());
            stmt.setString(5,customer.getPhoneNumber());
            stmt.setInt(6,customer.getId());
            int rowsupdated= stmt.executeUpdate();
            return rowsupdated>0;
        }catch (SQLException e){
            System.out.println("Error in updating: "+e.getMessage());
            return false;
        }
    }

}

