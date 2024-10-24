package com.ecom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
	@GetMapping("/index")
	public String index() {
		return "index";
	}
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	@GetMapping("/signup")
	public String signUp() {
		return "signup";
	}
	@GetMapping("/products")
	public String products() {
		return "product";
	}
	@GetMapping("/product")
	public String product() {
		return "view_product";
	}
	
}
