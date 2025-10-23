package com.poly.lab8.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.lab8.model.Account; // Import mới
import com.poly.lab8.service.AccountService; // Import mới

@Controller
public class AuthController {
    
    @Autowired
    AccountService accountService;
    
    @Autowired
    HttpSession session;

    @GetMapping("/auth/login")
    public String loginForm(Model model, @RequestParam(required = false) String message) {
        if (message != null) {
            model.addAttribute("message", message);
        }
        return "auth/login";
    }

    @PostMapping("/auth/login")
    public String loginProcess(Model model, 
            @RequestParam("username") String username, 
            @RequestParam("password") String password) {
        
        Account user = accountService.findById(username);
        
        if (user == null) {
            model.addAttribute("message", "Invalid email!"); 
        } else if (!user.getPassword().equals(password)) {
            model.addAttribute("message", "Invalid password!");
        } else {
            session.setAttribute("user", user);
            model.addAttribute("message", "Login successfully!");
            
            // Bài 5: Quay trở lại URI bảo mật trước đó
            String securityUri = (String) session.getAttribute("securityUri");
            if (securityUri != null) {
                session.removeAttribute("securityUri");
                return "redirect:" + securityUri; 
            }
        }
        
        return "auth/login";
    }
}