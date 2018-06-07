package com.example.StoperJava.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.StoperJava.model.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepo repository;

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return (List<User>) repository.findAll();
	}

	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		return repository.save(user);
	}

	@Override
	public Optional<User> findOne(Long id) {
		// TODO Auto-generated method stub
		return repository.findById(id);
	}

	@Override
	public User findByUsername(String username) {
		User user=new User();
		List<User> users = new ArrayList<User>();
		
		users=findAll();
		
		for (int i=0;i<users.size();i++){
			if (users.get(i).getEmail().equals(username)){
					user=users.get(i);			
			}
		}
		
		return user;
	}

}
