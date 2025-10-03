package com.poly.lab2.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class B4ProductController {
	List<Product> products = new ArrayList<Product>();
	
	@GetMapping("/product/formB4")
	public String form(Model model) {
	    Product p = new Product();
	    p.setName("Iphone 30");
	    p.setPrice(5000.0);
	    model.addAttribute("product", p); 
	    products = createItems();
	    System.out.println(products.size());
	    model.addAttribute("products", products);
	    return "product/formB4";
	}
	public List<Product> createItems() {
	    List<Product> products2 = new ArrayList<Product>();
	    products2.add(new Product("A", 1));
	    products2.add(new Product("B", 2));
	    return products2;
	}
	@PostMapping("/product/saveB4")
	public String save(@ModelAttribute Product p, Model model) {	  
	    products.add(p);
	    model.addAttribute("products", products);
	    return "product/formB4"; 
	}

}
