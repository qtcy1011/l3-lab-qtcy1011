package com.poly.lab8.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.poly.lab8.model.Account; // Import mới

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    HttpSession session;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        
        String uri = request.getRequestURI();
        Account user = (Account) session.getAttribute("user");

        // 1. Kiểm tra đăng nhập
        if (user == null) { 
            session.setAttribute("securityUri", uri); 
            response.sendRedirect(request.getContextPath() + "/auth/login?message=Vui lòng đăng nhập để truy cập trang này!"); 
            return false;
        }

        // 2. Kiểm tra vai trò admin
        if (uri.startsWith("/admin") && !uri.equals("/admin/home/index") && !user.isAdmin()) {
            response.sendRedirect(request.getContextPath() + "/auth/login?message=Bạn không có quyền truy cập trang quản trị!"); 
            return false;
        }
        
        return true;
    }
}