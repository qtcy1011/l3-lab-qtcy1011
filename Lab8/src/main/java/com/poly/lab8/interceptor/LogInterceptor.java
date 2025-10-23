package com.poly.lab8.interceptor;

import java.util.Date;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.poly.lab8.model.Account; // Import mới

@Component
public class LogInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        
        Account user = (Account) request.getSession().getAttribute("user");
        
        if (user != null) {
            String logMessage = String.format("LOG INTERCEPTOR: URI: %s, Thời gian: %s, Người dùng: %s",
                    request.getRequestURI(),
                    new Date(),
                    user.getFullname()); 
            
            System.out.println(logMessage);
        }
        
        return true;
    }
}