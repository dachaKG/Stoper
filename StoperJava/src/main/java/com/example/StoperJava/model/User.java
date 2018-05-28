package com.example.StoperJava.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private int gender;
	
	private String firstName;
	
	private String lastName;
	
	private int yearOfBirth;
	
	private String email;
	
	private String password;
	
	//private List<String> reviews;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(Long id, int gender, String firstName, String lastName, int yearOfBirth, String email, String password
			){//,List<String> reviews) {
		super();
		this.id = id;
		this.gender = gender;
		this.firstName = firstName;
		this.lastName = lastName;
		this.yearOfBirth = yearOfBirth;
		this.email = email;
		this.password = password;
		//this.reviews = reviews;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getYearOfBirth() {
		return yearOfBirth;
	}

	public void setYearOfBirth(int yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	//public List<String> getReviews() {
	//	return reviews;
	//}

	//public void setReviews(List<String> reviews) {
	//	this.reviews = reviews;
	//}
	
	
	
	
}
