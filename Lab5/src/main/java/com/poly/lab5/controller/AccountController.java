package com.poly.lab5.controller;

import com.poly.lab5.service.CookieService;
import com.poly.lab5.service.ParamService;
import com.poly.lab5.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

@Controller
public class AccountController {

    @Autowired
    CookieService cookieService;
    @Autowired
    ParamService paramService;
    @Autowired
    SessionService sessionService;

    @GetMapping("/account/login")
    public String login1(Model model) {

        String savedUsername = cookieService.getValue("user");
        model.addAttribute("username", savedUsername);
        return "/account/login";
    }

    @PostMapping("/account/login")
    public String login2() {

        String username = paramService.getString("username", "");
        String password = paramService.getString("password", "");
        boolean remember = paramService.getBoolean("remember", false);


        if (username.equals("poly") && password.equals("123")) {

            sessionService.set("username", username);


            if (remember) {
               
                cookieService.add("user", username, 10);
            } else {
      
                cookieService.remove("user");
            }

    
            return "redirect:/item/index";
        } else {

            return "/account/login";
        }
    }
}