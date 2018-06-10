package com.example.StoperJava.service;

import java.util.HashMap;

import org.springframework.stereotype.Service;

@Service
public class FirebaseRepo {

	
	private HashMap<String, String> repo=new HashMap<>();
	
	public void addToRepo(String username,String ID) {
		repo.put(username, ID);
	}
	
	public String getID(String username) {
		return repo.get(username);
	}
	
}
