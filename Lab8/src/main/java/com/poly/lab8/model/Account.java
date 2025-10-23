package com.poly.lab8.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private String username;
    private String password;
    private String fullname;
    private boolean admin = false; 
    
    public boolean isAdmin() {
        return this.admin;
    }
}