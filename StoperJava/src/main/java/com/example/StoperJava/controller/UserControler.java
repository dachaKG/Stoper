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
	
	private User testUser1=new User(0, "Danilo", "Acimovic", 1994, "danilo.a@yahoo.com", "123",
            "Dipl ing, elektroyehnike. Zaposle", "24554745", true, "pocetnik","+381");
	private User testUser2=new User(2L,1,"Kostas","Mitroglu",1984,"kkk","123");
	private User testUser3=new User(3L,1,"Scepan","Scekic",1979,"sss","123");
	
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
	@RequestMapping(value = "/login")
	public Boolean Login(@RequestBody LoginRequest loginRequest) {
		Boolean success=false;
		users.add(testUser1);
		users.add(testUser2);
		users.add(testUser3);
		System.out.println(loginRequest.getEmail());
		
		for (int i=0;i<users.size();i++){
			if (users.get(i).getEmail().equals(loginRequest.getEmail())){
				if(users.get(i).getPassword().equals(loginRequest.getPassword())){
					success=true;
				}
			}
		}
		return success;
	}
	
	@PutMapping
	@RequestMapping(value = "/register")
	public Boolean Register(@RequestBody User user) {
		
		System.out.println(user.getEmail());
		userService.saveUser(user);
		return true;
	}

}
