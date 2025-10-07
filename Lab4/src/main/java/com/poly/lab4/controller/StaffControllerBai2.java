package com.poly.lab4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import com.poly.lab4.entity.StaffB2;
import jakarta.validation.Valid;

@Controller
public class StaffControllerBai2{
	@RequestMapping("/staff/create/form1")
	public String createForm(Model model, @ModelAttribute("staff") StaffB2 staff) {
	model.addAttribute("message", "Vui lòng nhập thông tin nhân viên!");
	return "staff-validate";
	}
	@RequestMapping("/staff/create/save1")
	public String createSave(Model model,

	@RequestPart("photo_file") MultipartFile photoFile,
	@Valid @ModelAttribute("staff") StaffB2 staff, Errors errors) {
	if(!photoFile.isEmpty()) {
	staff.setPhoto(photoFile.getName());
	}
	if(errors.hasErrors()) {
	model.addAttribute("message", "Vui lòng sửa các lỗi sau!");
	} else {
	model.addAttribute("message", "Dữ liệu đã nhập đúng!");
	}
	return "staff-validate";
}
}
