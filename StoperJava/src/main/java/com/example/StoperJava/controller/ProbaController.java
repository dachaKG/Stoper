package com.example.StoperJava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.StoperJava.model.Ride;
import com.example.StoperJava.service.RideService;

@RestController
@RequestMapping(value = "/proba")
public class ProbaController {
	
	@Autowired
	RideService rideService;
	
	@GetMapping
	public String probaString() {
		return "proslo";
	}
	
	@PostMapping
	public ResponseEntity<Ride> addRide(@RequestBody Ride ride) {
		
		
		System.out.println(ride.getStartDestination());
		Ride newRide = rideService.saveRide(ride);
		
		return new ResponseEntity<Ride>(newRide, HttpStatus.OK);
	}

}
