package com.poly.lab8.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poly.lab8.service.MailService; // Import mới

@Controller
public class MailController {

    @Autowired
    MailService mailService;

    // Bài 1: Gửi email trực tiếp
    @ResponseBody
    @RequestMapping("/mail/send/direct")
    public String sendDirect() {
        try {
            mailService.send("receiver@gmail.com", "Test Subject Direct", "Body **gửi trực tiếp**."); 
            return "Mail của bạn đã được gửi đi trực tiếp";
        } catch (RuntimeException e) { 
            return "Lỗi khi gửi mail trực tiếp: " + e.getMessage();
        }
    }
    
    // Bài 2: Xếp mail vào hàng đợi
    @ResponseBody
    @RequestMapping("/mail/send")
    public String sendToQueue(Model model) {
        mailService.push("receiver@gmail.com", "Test Subject Queue", "Body **xếp vào hàng đợi**."); 
        return "Mail của bạn đã được xếp vào hàng đợi";
    }
}