package com.suryansh.groceryapp.Ui;

import com.suryansh.groceryapp.Model.Customer;
import com.suryansh.groceryapp.Services.CustomerService;

import java.util.Scanner;

public class CustomerMenu {
    private CustomerService customerService=new CustomerService();
    private Scanner scanner=new Scanner(System.in);

    public void start(){
        while(true){
            System.out.println("\n===Welcome To The Grocery App===");
            System.out.println("1.Register");
            System.out.println("2.Login");
            System.out.println("3.Admin login");
            System.out.println("4.Exit");
            System.out.println("Enter Your Choice");

            int choice=scanner.nextInt();
            scanner.nextLine();
            switch (choice){
                case 1:
                    registerCustomer();
                    break;
                case 2:
                    loginCustomer();
                    break;
                case 3:
                    AdminMenu adminMenu=new AdminMenu();
                    adminMenu.start();
                case 4:
                    System.out.println("Thank You For using Grocery App");
                    return;
                default:
                    System.out.println("Invalid Choice...Try Again");
            }
        }
    }
    public boolean isValidEmail(String email) {
        return email.matches("^[\\w.-]+@[\\w.-]+\\.\\w+$");
    }


    public void registerCustomer(){
        System.out.println("Customer Registration Initiated");
        System.out.print("Enter Your Name: ");
        String name=scanner.nextLine();
        System.out.print("Enter Your Email: ");
        String email=scanner.nextLine();
        if(isValidEmail(email)==false){
            System.out.println("Email write again");
            return;
        }
        System.out.print("Enter your Password: ");
        String password=scanner.nextLine();
        if (password.length() < 9) {
            System.out.println("Password must be at least 9 characters long.");
            return;
        }
        if (!Character.isUpperCase(password.charAt(0))) {
            System.out.println("Password must start with a capital letter.");
            return;
        }
        if (!password.matches(".*[!@#$%^&*()_+=<>?/].*")) {
            System.out.println("Password must contain at least one special character.");
            return;
        }

        System.out.print("Enter your Address");
        String address= scanner.nextLine();

        Customer customer=new Customer();
        customer.setName(name);
        customer.setEmail(email);
        customer.setPassword(password);
        customer.setAddress(address);

        boolean registered= customerService.registerCustomer(customer);

        if(registered){
            System.out.println("Registration is Successsfull");

        }else{
            System.out.println("Registration Failed Try Again");
        }
    }

    public void loginCustomer(){
        System.out.print("Enter the Email");
        String email =scanner.nextLine();
        System.out.println("Enter the password");
        String password=scanner.nextLine();

        Customer customer=customerService.login(email,password);

        if(customer!=null){
            System.out.println("Login Successfull! Welcome");
            showCustomerMenu(customer);


        }else{
            System.out.println("Failed to login..try again");
        }
    }
    public void showCustomerMenu(Customer customer) {
        boolean exit = false;

        while (!exit) {
            System.out.println("\n=== Customer Menu ===");
            System.out.println("1. View Profile");
            System.out.println("2. Update Profile");
            System.out.println("3.Browse Product");
            System.out.println("4. Logout");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            switch (choice) {
                case 1:
                    System.out.println("\nYour Profile:");
                    System.out.println("Name: " + customer.getName());
                    System.out.println("Email: " + customer.getEmail());
                    System.out.println("Phone: " + customer.getPhoneNumber());
                    System.out.println("Address: " + customer.getAddress());
                    break;

                case 2:
                    System.out.print("Enter new name: ");
                    customer.setName(scanner.nextLine());

                    System.out.print("Enter new phone number: ");
                    customer.setPhoneNumber(scanner.nextLine());

                    System.out.print("Enter new address: ");
                    customer.setAddress(scanner.nextLine());

                    boolean updated = customerService.updateCustomer(customer);
                    if (updated) {
                        System.out.println("Profile updated successfully!");
                    } else {
                        System.out.println("Update failed. Try again.");
                    }
                    break;

                case 3:
                    ProductMenu productMenu = new ProductMenu();
                    productMenu.showProductMenu();
                    break;

                case 4:
                    System.out.println("Logging out...");
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

}

