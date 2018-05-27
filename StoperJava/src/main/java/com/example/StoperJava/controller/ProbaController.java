package com.example.StoperJava.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.StoperJava.model.Ride;

@RestController
@RequestMapping(value = "/proba")
public class ProbaController {
	
	@GetMapping
	public String probaString() {
		return "proslo";
	}
	
	@PostMapping
	public Boolean addRide(@RequestBody Ride ride) {
		
		
		System.out.println(ride.getStartDestination());
		
		return true;
	}

}
