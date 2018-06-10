package com.example.StoperJava.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.StoperJava.dto.RideReservationDTO;
import com.example.StoperJava.model.Ride;
import com.example.StoperJava.model.User;
import com.example.StoperJava.service.RideService;
import com.example.StoperJava.service.UserService;

@RestController
@RequestMapping(value = "/ride")
public class RideController {
	
	@Autowired
	RideService rideService;
	
	@Autowired
	UserService userService;
	
	@GetMapping
	public List<Ride> findRides(){
		System.out.println("usao u ride");
		return rideService.findAll();
	}
	
	@PostMapping("/reserve")
	public Boolean reserve(@RequestBody RideReservationDTO rideReservationDTO) {
		boolean result = false;
		Ride ride = rideService.findOne(rideReservationDTO.getRideId()).get();
		if(ride != null) {
			User passenger = userService.findByEmail(rideReservationDTO.getPassengerEmail());
			if(passenger != null) {
				if(ride.getPassengerNumber() > 0) {
				ride.addPassenger(passenger);
				ride.setPassengerNumber(ride.getPassengerNumber()-1);
				rideService.saveRide(ride);
				result = true;
				}
			}
		}
		return result;
	}
	
	@PostMapping
	@RequestMapping(value = "/searchRides")
	public List<Ride> searchRides(@RequestBody Ride searchRide){
		List<Ride> rides=rideService.findAll();
		/*List<Ride> ridesToSend=new ArrayList<>();
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
		}*/
		
		return rides;
	}

}
