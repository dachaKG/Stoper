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
	
	/**
    private String biography;

    private String areaCall;
    
    private String phoneNumber;

    private boolean confirmed;

    private String level;
    
    private int speaking;
    
    private int smoking;
    
    private int music;
    
    private int pets;
    
    private int carCountry;
    
    private int carColor;
    
    private int carType;
    
    private String carRegistratonNumber;
    
    private String carBrand;
    
    private String carBrandModel;
    
    private int carYear;
    
	//private List<String> reviews;
*/
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
	/*

	public User(int gender, String first_name, String lastName, int year_of_birth, String email, String password,
			String biography, String area_call, String phoneNumber, boolean confirmed, String level) {
		super();
		this.gender = gender;
		this.first_name = first_name;
		this.lastName = lastName;
		this.year_of_birth = year_of_birth;
		this.email = email;
		this.password = password;
		this.biography = biography;
		this.areaCall = area_call;
		this.phoneNumber = phoneNumber;
		this.confirmed = confirmed;
		this.level = level;
	}

    public User(int gender, String first_name, String lastName, int year_of_birth, String email, String password, String biography, String phoneNumber, boolean confirmed, String level,String areaCall) {
        this.gender = gender;
        this.first_name = first_name;
        this.lastName = lastName;
        this.year_of_birth = year_of_birth;
        this.email = email;
        this.password = password;
        this.biography = biography;
        this.phoneNumber = phoneNumber;
        this.confirmed = confirmed;
        this.level = level;
        this.areaCall = areaCall;
    }
    User(int gender, String first_name, String lastName, int year_of_birth, String email, String password,
			String biography, String areaCall, String phoneNumber, boolean confirmed, String level, int speaking,
			int smoking, int music, int pets, int carCountry, int carColor, int carType, String carRegistratonNumber,
			String carBrand, String carBrandModel, int carYear) {
		super();
		this.gender = gender;
		this.first_name = first_name;
		this.lastName = lastName;
		this.year_of_birth = year_of_birth;
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
		this.carCountry = carCountry;
		this.carColor = carColor;
		this.carType = carType;
		this.carRegistratonNumber = carRegistratonNumber;
		this.carBrand = carBrand;
		this.carBrandModel = carBrandModel;
		this.carYear = carYear;
	}

	public User(int gender, String first_name, String lastName, int year_of_birth, String email, String password,
			String biography, String areaCall, String phoneNumber, boolean confirmed, String level, int speaking,
			int smoking, int music, int pets) {
		super();
		this.gender = gender;
		this.first_name = first_name;
		this.lastName = lastName;
		this.year_of_birth = year_of_birth;
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
*/
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
/*
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

	public int getCarCountry() {
		return carCountry;
	}

	public void setCarCountry(int carCountry) {
		this.carCountry = carCountry;
	}

	public int getCarColor() {
		return carColor;
	}

	public void setCarColor(int carColor) {
		this.carColor = carColor;
	}

	public int getCarType() {
		return carType;
	}

	public void setCarType(int carType) {
		this.carType = carType;
	}

	public String getCarRegistratonNumber() {
		return carRegistratonNumber;
	}

	public void setCarRegistratonNumber(String carRegistratonNumber) {
		this.carRegistratonNumber = carRegistratonNumber;
	}

	public String getCarBrand() {
		return carBrand;
	}

	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}

	public String getCarBrandModel() {
		return carBrandModel;
	}

	public void setCarBrandModel(String carBrandModel) {
		this.carBrandModel = carBrandModel;
	}

	public int getCarYear() {
		return carYear;
	}

	public void setCarYear(int carYear) {
		this.carYear = carYear;
	}

	
	
	//public List<String> getReviews() {
	//	return reviews;
	//}

	//public void setReviews(List<String> reviews) {
	//	this.reviews = reviews;
	//}
	
	*/
	
	
}
