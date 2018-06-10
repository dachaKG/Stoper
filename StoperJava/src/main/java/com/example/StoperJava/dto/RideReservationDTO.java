package com.example.StoperJava.dto;

public class RideReservationDTO {
	
	private String passengerEmail;
	private Long rideId;
	
	public RideReservationDTO() {
		super();
	}
	public String getPassengerEmail() {
		return passengerEmail;
	}
	public void setPassengerEmail(String passengerEmail) {
		this.passengerEmail = passengerEmail;
	}
	public Long getRideId() {
		return rideId;
	}
	public void setRideId(Long rideId) {
		this.rideId = rideId;
	}
	
	

}
