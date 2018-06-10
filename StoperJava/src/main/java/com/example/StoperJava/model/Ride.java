package com.example.StoperJava.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@Entity
public class Ride implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String startDestination;

	private String endDestination;

	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date rideDate;

	private Integer passengerNumber;

	private Double price;

	private String note;

	private Integer maxPassengerNum;
	
	@ManyToOne
	@JoinColumn(name="DRIVER_ID")
	private User driver;
		
	@ManyToMany(cascade=CascadeType.ALL)  
    @JoinTable(name="rides_passengers", joinColumns=@JoinColumn(name="ride_id"), inverseJoinColumns=@JoinColumn(name="user_id"))  
	private Set<User> passengers;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStartDestination() {
		return startDestination;
	}

	public void setStartDestination(String startDestination) {
		this.startDestination = startDestination;
	}

	public String getEndDestination() {
		return endDestination;
	}

	public void setEndDestination(String endDestination) {
		this.endDestination = endDestination;
	}

	public Date getRideDate() {
		return rideDate;
	}

	public void setRideDate(Date rideDate) {
		this.rideDate = rideDate;
	}

	public Integer getPassengerNumber() {
		return passengerNumber;
	}

	public void setPassengerNumber(Integer passengerNumber) {
		this.passengerNumber = passengerNumber;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getMaxPassengerNum() {
		return maxPassengerNum;
	}

	public void setMaxPassengerNum(Integer maxPassengerNum) {
		this.maxPassengerNum = maxPassengerNum;
	}

	@Override
	public String toString() {
		return "Ride [id=" + id + ", startDestination=" + startDestination + ", endDestination=" + endDestination
				+ ", rideDate=" + rideDate + ", passengerNumber=" + passengerNumber + ", price=" + price + ", note="
				+ note + "]";
	}
	
	public User getDriver() {
		return driver;
	}

	public void setDriver(User driver) {
		this.driver = driver;
	}

	public Set<User> getPassengers() {
		return passengers;
	}

	public void setPassengers(Set<User> passengers) {
		this.passengers = passengers;
	}

	public boolean addPassenger(User passenger) {
		if(passengers == null) {
			passengers = new HashSet<User>();
		}
		return passengers.add(passenger);
	}
	

}
