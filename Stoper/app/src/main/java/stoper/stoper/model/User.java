package stoper.stoper.model;

public class User {

    private Long id;

    private int gender;

    private String firstName;

    private String lastName;

    private int yearOfBirth;

    private String email;

    private String password;

    private String biography;

    private String phoneNumber;

    private String areaCall;

    private boolean confirmed;

    private String level;

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
}
