package com.example.StoperJava.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.StoperJava.dto.RideReservationDTO;
import com.example.StoperJava.model.Ride;
import com.example.StoperJava.model.User;
import com.example.StoperJava.service.AndroidPushNotificationsService;
import com.example.StoperJava.service.FirebaseRepo;
import com.example.StoperJava.service.RideService;
import com.example.StoperJava.service.UserService;

@RestController
@RequestMapping(value = "/ride")
public class RideController {
	
	@Autowired
	RideService rideService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	AndroidPushNotificationsService androidPushNotificationsService;
	
	@Autowired
	FirebaseRepo firebaseRepo;
	
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
				try {
					reservationNotification(ride,passenger);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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

	
	public void reservationNotification(Ride r,User putnik) throws JSONException{
		
		String token = firebaseRepo.getID(r.getDriver().getEmail());
		System.out.println(token);
		if(token==null) {
			System.out.println("nema usera prijavljenog ni na jednom telefonu");
			return;
		}
		
		JSONObject body = new JSONObject();
		body.put("to", token);
		//body.put("priority", "high");
 
		JSONObject notification = new JSONObject();
		notification.put("title", "Rezervacija");
		notification.put("body", "Rezervisao je putnik "+putnik.getFirstName());
		
		JSONObject data = new JSONObject();
		data.put("Key-1", "JSA Data 1");
		data.put("Key-2", "JSA Data 2");
		data.put("type", "rezervacija");
		data.put("message","Rezervisao je putnik "+putnik.getFirstName());
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
			System.out.println(firebaseResponse);
			ocjenjivanjeNotifikacija(r);
			//return new ResponseEntity<>(firebaseResponse, HttpStatus.OK);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
 
		//return new ResponseEntity<>("Push Notification ERROR!", HttpStatus.BAD_REQUEST);
		
	}
	

	public void ocjenjivanjeNotifikacija(Ride ride){
			for (User u : ride.getPassengers()) {
				try {
					ocenjivanje(ride.getDriver().getEmail(), u.getEmail());
					ocenjivanje(u.getEmail(),ride.getDriver().getEmail());
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		System.out.println("Poslati notificationi za za ocenjivanje");
		
	}
	
	
	public void ocenjivanje(String mailCijizoves,String mailKogaOcjenjujes) throws JSONException{
		JSONObject body = new JSONObject();
		
		String token = firebaseRepo.getID(mailCijizoves);
		System.out.println(token);
		if(token==null) {
			System.out.println("nema usera prijavljenog ni na jednom telefonu");
			return;
		}
		body.put("to", token);

		JSONObject notification = new JSONObject();
		notification.put("title", "Rezervacija");
		notification.put("body", "Ocenjujete putnik "+mailKogaOcjenjujes);
		
		JSONObject data = new JSONObject();
		data.put("Key-1", "JSA Data 1");
		data.put("Key-2", "JSA Data 2");
		data.put("type", "ocena");
		data.put("message","Ocenjujete putnik "+mailKogaOcjenjujes);
		data.put("mailOcenjivanjog", mailKogaOcjenjujes);
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
			System.out.println(firebaseResponse);
			//return new ResponseEntity<>(firebaseResponse, HttpStatus.OK);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
 
		//return new ResponseEntity<>("Push Notification ERROR!", HttpStatus.BAD_REQUEST);
	}
	
}
