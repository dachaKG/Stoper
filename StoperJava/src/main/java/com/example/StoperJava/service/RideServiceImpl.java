package com.example.StoperJava.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.StoperJava.model.Ride;

@Service
@Transactional
public class RideServiceImpl implements RideService {
	
	@Autowired
	RideRepo repository;

	@Override
	public List<Ride> findAll() {
		return (List<Ride>) repository.findAll();
	}

	@Override
	public Ride saveRide(Ride ride) {
		return repository.save(ride);
	}

	@Override
	public Optional<Ride> findOne(Long id) {
		return repository.findById(id);
	}

}
