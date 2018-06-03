package com.example.StoperJava.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.StoperJava.model.LoginRequest;
import com.example.StoperJava.model.User;
import com.example.StoperJava.service.UserService;

@Controller
@RestController
@RequestMapping(value = "/user")
public class UserControler {
	
	@Autowired
	UserService userService;
	
	private List<User> users =new ArrayList<User>();
	private  User logedUser=new User();
	
	@PostMapping
	@RequestMapping(value = "/login")
	public Boolean Login(@RequestBody LoginRequest loginRequest) {
		users=userService.findAll();
		Boolean success=false;
		for (int i=0;i<users.size();i++){
			if (users.get(i).getEmail().equals(loginRequest.getEmail())){
				if(users.get(i).getPassword().equals(loginRequest.getPassword())){
					success=true;
					
				}
			}
		}
		return success;
	}
	
	@PostMapping
	@RequestMapping(value = "/getLogedUser")
	public User GetLogedUser(@RequestBody String email) {
		return logedUser=userService.findByUsername(email);
	}
	
	@PutMapping
	@RequestMapping(value = "/register")
	public Boolean Register(@RequestBody User user) {
		users=userService.findAll();
		for (int i=0;i<users.size();i++){
			if (users.get(i).getEmail().equals(user.getEmail())){
				return false;
			}
		}
		userService.saveUser(user);
		return true;
	}

}
