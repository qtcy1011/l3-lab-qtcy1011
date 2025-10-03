package com.poly.lab3.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.lab3.entity.Staff;



@Controller
public class StaffController {
	@RequestMapping("/staff/detail") 
	public String detail(Model model) { 
	Staff staff = Staff.builder() 
	.id("user@gmail.com") 
	.fullname("nguyễn văn user") 
	.level(2) 
	.build(); 
	model.addAttribute("staff", staff); 
	return "staff-detail"; 
	}
}
	
