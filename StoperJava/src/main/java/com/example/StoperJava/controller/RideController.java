package com.example.StoperJava.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@PostMapping
	@RequestMapping(value = "/searchRides")
	public List<Ride> searchRides(@RequestBody Ride searchRide){
		List<Ride> rides=rideService.findAll();
		List<Ride> ridesToSend=new ArrayList<>();
		for(Ride r : rides) {
			if(r.getStartDestination().equals(searchRide.getStartDestination())) {
				if(r.getEndDestination().equals(searchRide.getEndDestination())) {
					if(r.getRideDate().compareTo(searchRide.getRideDate())==0) {
						if(r.getPassengerNumber()>=searchRide.getPassengerNumber()) {
							ridesToSend.add(r);
						}
					}
				}
			}
		}
		
		return ridesToSend;
	}

}
