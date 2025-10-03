package com.poly.lab1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class Bai4Controller {

    @Autowired
    HttpServletRequest request;

    @GetMapping("/form")
    public String form() {
        return "form";
    }

    @PostMapping("/check")
    public String check(Model model) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if ("poly".equals(username) && "123".equals(password)) {
            model.addAttribute("message", "Đăng nhập thành công!");
            model.addAttribute("success", true);
        } else {
            model.addAttribute("message", "Tên đăng nhập hoặc mật khẩu không đúng. Vui lòng thử lại.");
            model.addAttribute("success", false);
        }

        return "form";
    }
}