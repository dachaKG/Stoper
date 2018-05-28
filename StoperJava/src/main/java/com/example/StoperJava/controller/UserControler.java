package com.example.StoperJava.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.StoperJava.model.LoginRequest;
import com.example.StoperJava.model.User;

@RestController
@RequestMapping(value = "/user")
public class UserControler {
	
	@GetMapping
	public String test() {
		return "test test proba 1 2 1 2";
	}
	
	@PostMapping
	@RequestMapping(value = "/login")
	public Boolean Login(@RequestBody LoginRequest loginRequest) {
		
		System.out.println(loginRequest.getEmail());
		return true;
	}
	
	@PostMapping
	@RequestMapping(value = "/register")
	public Boolean Register(@RequestBody User user) {
		
		System.out.println(user.getGender());
		return true;
	}

}
