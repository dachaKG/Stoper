package com.example.StoperJava.service;

import java.util.List;
import java.util.Optional;

import com.example.StoperJava.model.Ride;

public interface RideService {
	
	public List<Ride> findAll();
	
	public Ride saveRide(Ride ride);
	
	public Optional<Ride> findOne(Long id);

}
