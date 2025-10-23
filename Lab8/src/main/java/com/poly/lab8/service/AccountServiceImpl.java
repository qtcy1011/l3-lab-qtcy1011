package com.poly.lab8.service;

import java.util.HashMap;
import java.util.Map;

import jakarta.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.poly.lab8.model.Account; // Import mới

@Service
public class AccountServiceImpl implements AccountService {
    
    private Map<String, Account> accounts = new HashMap<>();
    
    @PostConstruct
    public void init() {
        // user/123 (non-admin), admin/123 (admin)
        accounts.put("user", new Account("user", "123", "Người Dùng", false)); 
        accounts.put("admin", new Account("admin", "123", "Quản Trị Viên", true)); 
    }

    @Override
    public Account findById(String username) {
        return accounts.get(username);
    }
}