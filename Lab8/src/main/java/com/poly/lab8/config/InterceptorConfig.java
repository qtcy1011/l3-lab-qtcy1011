package com.poly.lab8.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.poly.lab8.interceptor.AuthInterceptor;
import com.poly.lab8.interceptor.LogInterceptor; 

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    AuthInterceptor authInterceptor; 
    
    @Autowired
    LogInterceptor logInterceptor; 

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        
        String[] securePaths = {
            "/admin/**", 
            "/account/change-password", 
            "/account/edit-profile", 
            "/order/**"
        };
        
        String[] excludePaths = {
            "/admin/home/index" 
        };
        
        // Bài 5: Cấu hình AuthInterceptor (Bảo mật)
        registry.addInterceptor(authInterceptor)
            .addPathPatterns(securePaths)
            .excludePathPatterns(excludePaths);
        
        // Bài 6: Cấu hình LogInterceptor (Ghi log)
        registry.addInterceptor(logInterceptor)
            .addPathPatterns(securePaths)
            .excludePathPatterns(excludePaths);
    }
}