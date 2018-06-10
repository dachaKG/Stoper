package com.example.StoperJava.controller;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.StoperJava.model.LoginRequest;
import com.example.StoperJava.model.Ride;
import com.example.StoperJava.model.User;
import com.example.StoperJava.service.AndroidPushNotificationsService;
import com.example.StoperJava.service.FirebaseRepo;
import com.example.StoperJava.service.RideService;
import com.example.StoperJava.service.UserService;

@RestController
@RequestMapping(value = "/proba")
public class ProbaController {
	
	@Autowired
	RideService rideService;
	
	@Autowired
	AndroidPushNotificationsService androidPushNotificationsService;
	
	@Autowired
	FirebaseRepo firebaseRepo;
	
	@Autowired
	UserService userService;
	
	@GetMapping
	public ResponseEntity<String> probaString() throws JSONException{
		JSONObject body = new JSONObject();
		body.put("to", "cGDjDKMuV-A:APA91bEIHZtC0hJxLkR6ifJdklVbhP4EGRYJsdPEHX_9SlX7aEMDXWaUyCjxgYsx0g3NlmPBbwk8Abrod0BB3Pa8ewdBlKaj4PJKvX86WmTxZYuaMYf2zcNxyUt1GW1MKcNuoHG2nxyK");
		//body.put("priority", "high");
 
		JSONObject notification = new JSONObject();
		notification.put("title", "JSA Notification");
		notification.put("body", "Happy Message!");
		
		JSONObject data = new JSONObject();
		data.put("Key-1", "JSA Data 1");
		data.put("Key-2", "JSA Data 2");
 
		body.put("notification", notification);
		body.put("data", data);
 
/**
		{
		   "notification": {
		      "title": "JSA Notification",
		      "body": "Happy Message!"
		   },
		   "data": {
		      "Key-1": "JSA Data 1",
		      "Key-2": "JSA Data 2"
		   },
		   "to": "/topics/JavaSampleApproach",
		   "priority": "high"
		}
*/
 
		HttpEntity<String> request = new HttpEntity<>(body.toString());
 
		CompletableFuture<String> pushNotification = androidPushNotificationsService.send(request);
		CompletableFuture.allOf(pushNotification).join();
 
		try {
			String firebaseResponse = pushNotification.get();
			
			return new ResponseEntity<>(firebaseResponse, HttpStatus.OK);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
 
		return new ResponseEntity<>("Push Notification ERROR!", HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping
	@RequestMapping(value = "/{driverEmail}")
	public ResponseEntity<Ride> addRide(@PathVariable String driverEmail, @RequestBody Ride ride) {
		User driver = userService.findByEmail(driverEmail);
		ride.setDriver(driver);
		ride.setMaxPassengerNum(ride.getPassengerNumber());
		Ride newRide = rideService.saveRide(ride);
		System.out.println(newRide.toString());
		return new ResponseEntity<Ride>(newRide, HttpStatus.OK);
	}
	
	@PostMapping
	@RequestMapping(value = "/addToken")
	public ResponseEntity<String> addToken(@RequestBody LoginRequest lr) {
		firebaseRepo.addToRepo(lr.getEmail(), lr.getPassword());
		System.out.println(lr.getPassword());
		return new ResponseEntity<String>(lr.getEmail(), HttpStatus.OK);
	}

}
