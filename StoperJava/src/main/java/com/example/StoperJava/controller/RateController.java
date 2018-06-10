package com.example.StoperJava.controller;

import java.sql.Date;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.StoperJava.model.LoginRequest;
import com.example.StoperJava.model.Rate;
import com.example.StoperJava.model.RateRequest;
import com.example.StoperJava.model.User;
import com.example.StoperJava.service.RateService;
import com.example.StoperJava.service.UserService;

@Controller
@RestController
@RequestMapping(value = "/rate")
public class RateController {

	

	@Autowired
	RateService rateService;
	
	@Autowired
	UserService userService;
	
	private User user1=new User(); //onaj ko komentarise
	private User user2=new User(); // onaj kome se komentarise nesto
	
	private Rate rate=new Rate();
	
	
	@PutMapping
	@RequestMapping(value = "/rateRide")
	public Boolean RateRide(@RequestBody RateRequest rateRequest) {
		Boolean success=false;
		user1=userService.findByEmail(rateRequest.getEvaluatorEmail());
		user2=userService.findByEmail(rateRequest.getRecieverEmail());
		
		java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		System.out.println(date);
		
		rate.setComment(rateRequest.getComment());
		rate.setDate(date);
		rate.setEvaluator(user1);
		rate.setMark(rateRequest.getMark());
		rate.setReciever(user2);
		
		rateService.saveRate(rate);
		success=true;
		return success;
	}
	
}
