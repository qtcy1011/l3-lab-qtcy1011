package com.poly.lab2.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

@Controller
public class B3ProductController {
 @GetMapping("/product/formB3")
    public String form(Product product) {
        product.setName("Iphone");
        product.setPrice(20.0);
        return "product/formB3"; 
    }

 @PostMapping("/product/saveB3")
    public String save(@ModelAttribute Product product, Model model) {
        product.setPrice(product.getPrice() * 2);
        return "product/formB3"; 
}
}