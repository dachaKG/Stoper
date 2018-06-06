package com.example.StoperJava.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.StoperJava.model.Ride;
import com.example.StoperJava.service.RideService;

@RestController
@RequestMapping(value = "/ride")
public class RideController {
	
	@Autowired
	RideService rideService;
	
	@GetMapping
	public List<Ride> findRides(){
		System.out.println("usao u ride");
		return rideService.findAll();
	}

}
