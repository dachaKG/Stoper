package com.example.StoperJava.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;



/**
 * @author Danilo
 *
 */
/**
 * @author Danilo
 *
 */
@Entity
@Table(name="users")
public class User{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer gender;
	
	private String firstName;
	
	private String lastName;
	
	private Integer yearOfBirth;
	
	private String email;
	
	private String password;
	
	@Column(nullable = true)
    private String biography;

	@Column(nullable = true)
    private String areaCall;

	@Column(nullable = true)
    private String phoneNumber;

	@Column(nullable = true)
    private Boolean confirmed;

	@Column(nullable = true)
    private String level;

	@Column(nullable = true)
    private Integer speaking;

	@Column(nullable = true)
    private Integer smoking;

	@Column(nullable = true)
    private Integer music;

	@Column(nullable = true)
    private Integer pets;

	@Column(nullable = true)
    private Integer carCountry;

	@Column(nullable = true)
    private Integer carColor;

	@Column(nullable = true)
    private Integer carType;

	@Column(nullable = true)
    private String carRegistratonNumber;

	@Column(nullable = true)
    private String carBrand;

	@Column(nullable = true)
    private String carBrandModel;

	@Column(nullable = true)
    private Integer carYear;
    
	@Lob
	 @JsonInclude
	@Basic(fetch = FetchType.EAGER)
    private byte[] profileImage;

	//private List<String> reviews;

	@OneToMany(mappedBy="evaluator")
	private List<Rate> assignedComments = new ArrayList<Rate>();

	@OneToMany(mappedBy="reciever")
	private List<Rate> recievedComments = new ArrayList<Rate>();
	
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



	public User(String email, String first_name,int gender, String lastName,String password, int yearOfBirth){//,List<String> reviews) {
		super();
		//this.id = id;
		this.gender = gender;
		this.firstName = first_name;
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
    
	public User(int gender, String firstName, String lastName, int yearOfBirth, String email, String password,
			String biography, String areaCall, String phoneNumber, boolean confirmed, String level, int speaking,
			int smoking, int music, int pets, int carCountry, int carColor, int carType, String carRegistratonNumber,
			String carBrand, String carBrandModel, int carYear) {
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
		this.carCountry = carCountry;
		this.carColor = carColor;
		this.carType = carType;
		this.carRegistratonNumber = carRegistratonNumber;
		this.carBrand = carBrand;
		this.carBrandModel = carBrandModel;
		this.carYear = carYear;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
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

	public Integer getYearOfBirth() {
		return yearOfBirth;
	}

	public void setYearOfBirth(Integer yearOfBirth) {
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

	public void setAreaCall(String areaCall) {
		this.areaCall = areaCall;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Boolean getConfirmed() {
		return confirmed;
	}

	public void setConfirmed(Boolean confirmed) {
		this.confirmed = confirmed;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Integer getSpeaking() {
		return speaking;
	}

	public void setSpeaking(Integer speaking) {
		this.speaking = speaking;
	}

	public Integer getSmoking() {
		return smoking;
	}

	public void setSmoking(Integer smoking) {
		this.smoking = smoking;
	}

	public Integer getMusic() {
		return music;
	}

	public void setMusic(Integer music) {
		this.music = music;
	}

	public Integer getPets() {
		return pets;
	}

	public void setPets(Integer pets) {
		this.pets = pets;
	}

	public Integer getCarCountry() {
		return carCountry;
	}

	public void setCarCountry(Integer carCountry) {
		this.carCountry = carCountry;
	}

	public Integer getCarColor() {
		return carColor;
	}

	public void setCarColor(Integer carColor) {
		this.carColor = carColor;
	}

	public Integer getCarType() {
		return carType;
	}

	public void setCarType(Integer carType) {
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

	public Integer getCarYear() {
		return carYear;
	}

	public void setCarYear(Integer carYear) {
		this.carYear = carYear;
	}

	public byte[] getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(byte[] profileImage) {
		this.profileImage = profileImage;
	}

	public List<Rate> getAssignedComments() {
		return assignedComments;
	}

	public void setAssignedComments(List<Rate> assignedComments) {
		this.assignedComments = assignedComments;
	}

	public List<Rate> getRecievedComments() {
		return recievedComments;
	}

	public void setRecievedComments(List<Rate> recievedComments) {
		this.recievedComments = recievedComments;
	}	
	//public List<String> getReviews() {
	//	return reviews;
	//}

	//public void setReviews(List<String> reviews) {
	//	this.reviews = reviews;
	//}
	
	
	
	
}
