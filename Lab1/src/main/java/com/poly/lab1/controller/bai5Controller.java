package com.poly.lab1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class bai5Controller { 
	@GetMapping("/hcn")
    public String hienThiForm() { 
        return "trangtinh"; 
	}
    @PostMapping("/tinhToan")
    public String tinhToan(
            @RequestParam("chieuRong") double chieuRong, 
            @RequestParam("chieuDai") double chieuDai, 
            Model model) {
    	  if (chieuDai < chieuRong) {
              model.addAttribute("error", "Chiều dài phải >= chiều rộng.");
              return "trangtinh";
          }


        double dienTich = chieuRong * chieuDai;
        double chuVi = 2 * (chieuRong + chieuDai); 

        model.addAttribute("dienTich", dienTich); 
        model.addAttribute("chuVi", chuVi); 

        return "trangtinh";
    }
}