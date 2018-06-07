package com.example.StoperJava.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	
	private User logedUser=new User();
	
	
	private List<User> users = new ArrayList<User>();
	
	@GetMapping
	public String test() {
		return "test test proba 1 2 1 2";
	}
	@GetMapping("/{id}")
	public User get(@PathVariable Long id){
		User user = userService.findOne(id).get();
		return user;
	}
	
	@PostMapping
	@RequestMapping(value = "/loginnnnslslsln")
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
	@RequestMapping(value = "/login")
	public User LogIn(@RequestBody LoginRequest loginRequest) {
		
		users=userService.findAll();
		Boolean success=false;
		for (int i=0;i<users.size();i++){
			
			if (users.get(i).getEmail().equals(loginRequest.getEmail())){
				
				if(users.get(i).getPassword().equals(loginRequest.getPassword())){
					
					logedUser=userService.findByUsername(loginRequest.getEmail());
					System.out.println("ssssssss  ---- "+logedUser.getEmail());
				}
			}
		}
		return logedUser;
	}
	
	//@PostMapping
	//@RequestMapping(value = "/getLogedUser")
	//public User GetLogedUser(@RequestBody String email) {
	//	return logedUser=userService.findByUsername(email);
	//}
	
	@PutMapping
	@RequestMapping(value = "/register")
	public Boolean Register(@RequestBody User user) {
		users=userService.findAll();
		System.out.println(user.getEmail());
		for (int i=0;i<users.size();i++){
			if (users.get(i).getEmail().equals(user.getEmail())){
				return false;
			}
		}
		userService.saveUser(user);
		return true;
	}

}
