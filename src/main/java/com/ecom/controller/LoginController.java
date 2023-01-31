package com.ecom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping("/signin")
	public String showLoginForm() {
		return "login";
	}
	
	@GetMapping("/process_login")
	public String home() {
		return "index";
	}
}
