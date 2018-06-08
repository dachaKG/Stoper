package com.example.StoperJava.service;

import java.util.List;
import java.util.Optional;

import com.example.StoperJava.model.User;

public interface UserService {

	public List<User> findAll();
	
	public User saveUser(User user);
	
	public Optional<User> findOne(Long id);
	
	public User findByUsername(String username);
	
	public User findByEmail(String email);

	
}
