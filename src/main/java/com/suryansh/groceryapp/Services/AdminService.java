package com.suryansh.groceryapp.Services;

import com.suryansh.groceryapp.Model.Admin;
import com.suryansh.groceryapp.Repository.AdminRepository;

public class AdminService {
    private AdminRepository adminRepo = new AdminRepository();

    public Admin login(String username, String password) {
        return adminRepo.login(username, password);
    }
}

