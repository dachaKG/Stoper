package com.example.StoperJava.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private int gender;
	
	private String first_name;
	
	private String lastName;
	
	private int year_of_birth;
	
	private String email;
	
	private String password;
	
	//private List<String> reviews;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String email, String first_name,int gender, String lastName,String password, int year_of_birth){//,List<String> reviews) {
		super();
		//this.id = id;
		this.gender = gender;
		this.first_name = first_name;
		this.lastName = lastName;
		this.year_of_birth = year_of_birth;
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

	public String getfirst_name() {
		return first_name;
	}

	public void setfirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getyear_of_birth() {
		return year_of_birth;
	}

	public void setyear_of_birth(int year_of_birth) {
		this.year_of_birth = year_of_birth;
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
