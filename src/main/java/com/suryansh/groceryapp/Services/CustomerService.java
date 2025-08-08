package com.suryansh.groceryapp.Services;

import com.suryansh.groceryapp.Model.Customer;
import com.suryansh.groceryapp.Repository.CustomerRepository;

public class CustomerService {
    private CustomerRepository customerRepository=new CustomerRepository();
    public boolean registerCustomer(Customer customer){
        return customerRepository.registerCustomer(customer);
    }

    public Customer login(String email,String password){
        return customerRepository.findbyemailandpassword(email, password);
    }

    public boolean updateCustomer(Customer customer){

        return CustomerRepository.updateCustomer(customer);
    }
}

