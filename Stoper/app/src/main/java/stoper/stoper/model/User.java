package stoper.stoper.model;

public class User {

    private Long id;

    private int gender;

    private String firstName;

    private String lastName;

    private int year_of_birth;

    private String email;

    private String password;

    private String biography;

    private String phoneNumber;

    private String areaCall;

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

    private byte[] profileImage;

    public  User() {
        super();
    }

    public User(int gender, String firstName, String lastName, int year_of_birth, String email, String password, String biography, String phoneNumber, String areaCall, boolean confirmed, String level, int speaking, int smoking, int music, int pets) {
        this.gender = gender;
        this.firstName = firstName;
        this.lastName = lastName;
        this.year_of_birth = year_of_birth;
        this.email = email;
        this.password = password;
        this.biography = biography;
        this.phoneNumber = phoneNumber;
        this.areaCall = areaCall;
        this.confirmed = confirmed;
        this.level = level;
        this.speaking = speaking;
        this.smoking = smoking;
        this.music = music;
        this.pets = pets;
    }

    public User(int gender, String firstName, String lastName, int year_of_birth, String email, String password, String biography, String phoneNumber, boolean confirmed, String level, String areaCall) {
        this.gender = gender;
        this.firstName = firstName;
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

    public User(int gender, String firstName, String lastName, int year_of_birth, String email, String password, String biography, String phoneNumber, String areaCall, boolean confirmed, String level, int speaking, int smoking, int music, int pets, int carCountry, int carColor, int carType, String carRegistratonNumber, String carBrand, String carBrandModel, int carYear) {
        this.gender = gender;
        this.firstName = firstName;
        this.lastName = lastName;
        this.year_of_birth = year_of_birth;
        this.email = email;
        this.password = password;
        this.biography = biography;
        this.phoneNumber = phoneNumber;
        this.areaCall = areaCall;
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
        return year_of_birth;
    }

    public void setYearOfBirth(int year_of_birth) {
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

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
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

    public Long getId(){
        return this.id;
    }

    public void setId(Long idd){
        this.id = id;
    }

    public String getAreaCall() {
        return areaCall;
    }

    public void setAreaCall(String areaCall) {
        this.areaCall = areaCall;
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

    public byte[] getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(byte[] profileImage) {
        this.profileImage = profileImage;
    }
}
