package com.example.StoperJava.service;

import org.springframework.data.repository.CrudRepository;

import com.example.StoperJava.model.User;

public interface UserRepo extends CrudRepository<User, Long>{

	public User findUserByEmail(String email);
}
