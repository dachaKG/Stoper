package com.example.StoperJava.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.StoperJava.model.Ride;

@RestController
@RequestMapping(value = "/search")
public class SearchController {
	
	@GetMapping
	public ArrayList<Ride> probaString() {
		ArrayList<Ride> lista=new ArrayList<>();
		Ride r1=new Ride();
		Ride r2=new Ride();
		lista.add(r1);
		lista.add(r2);
		return lista;
	}
	
	@PostMapping
	public Boolean addRide(@RequestBody Ride ride) {
		
		
		System.out.println(ride.getStartDestination());
		
		return true;
	}

}
