package com.ecom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.config.JwtTokenUtil;
import com.ecom.config.MyUserDetailsService;
import com.ecom.dtos.JwtRequest;
import com.ecom.services.UserServices;

@RestController
public class AuthenticationController<T> {
	
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@Autowired
	private UserServices<T> userServices;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@PostMapping("/login")
	public T generateToken(@RequestBody JwtRequest jwtRequest) {
		System.out.println(jwtRequest);
		return userServices.loginUser(jwtRequest);
		
	}
	
}
