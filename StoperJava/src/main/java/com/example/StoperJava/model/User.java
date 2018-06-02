package com.example.StoperJava.model;

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
	
    private String biography;

    private String areaCall;
    
    private String phoneNumber;

    private boolean confirmed;

    private String level;
    
    private int speaking;
    
    private int smoking;
    
    private int music;
    
    private int pets;
    
	//private List<String> reviews;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public User(int gender, String firstName, String lastName, int yearOfBirth, String email, String password,
			String biography, String areaCall, String phoneNumber, boolean confirmed, String level, int speaking,
			int smoking, int music, int pets) {
		super();
		this.gender = gender;
		this.firstName = firstName;
		this.lastName = lastName;
		this.yearOfBirth = yearOfBirth;
		this.email = email;
		this.password = password;
		this.biography = biography;
		this.areaCall = areaCall;
		this.phoneNumber = phoneNumber;
		this.confirmed = confirmed;
		this.level = level;
		this.speaking = speaking;
		this.smoking = smoking;
		this.music = music;
		this.pets = pets;
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

	public User(int gender, String firstName, String lastName, int yearOfBirth, String email, String password,
			String biography, String area_call, String phoneNumber, boolean confirmed, String level) {
		super();
		this.gender = gender;
		this.firstName = firstName;
		this.lastName = lastName;
		this.yearOfBirth = yearOfBirth;
		this.email = email;
		this.password = password;
		this.biography = biography;
		this.areaCall = area_call;
		this.phoneNumber = phoneNumber;
		this.confirmed = confirmed;
		this.level = level;
	}

    public User(int gender, String firstName, String lastName, int yearOfBirth, String email, String password, String biography, String phoneNumber, boolean confirmed, String level,String areaCall) {
        this.gender = gender;
        this.firstName = firstName;
        this.lastName = lastName;
        this.yearOfBirth = yearOfBirth;
        this.email = email;
        this.password = password;
        this.biography = biography;
        this.phoneNumber = phoneNumber;
        this.confirmed = confirmed;
        this.level = level;
        this.areaCall = areaCall;
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

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public String getAreaCall() {
		return areaCall;
	}

	public void setAreaCall(String area_call) {
		this.areaCall = area_call;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public boolean isConfirmed() {
		return confirmed;
	}

	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public int getSpeaking() {
		return speaking;
	}

	public void setSpeaking(int speaking) {
		this.speaking = speaking;
	}

	public int getSmoking() {
		return smoking;
	}

	public void setSmoking(int smoking) {
		this.smoking = smoking;
	}

	public int getMusic() {
		return music;
	}

	public void setMusic(int music) {
		this.music = music;
	}

	public int getPets() {
		return pets;
	}

	public void setPets(int pets) {
		this.pets = pets;
	}

	
	
	//public List<String> getReviews() {
	//	return reviews;
	//}

	//public void setReviews(List<String> reviews) {
	//	this.reviews = reviews;
	//}
	
	
	
	
}
