package stoper.stoper.DTO;

public class UserPersonalDataDTO {

    private String email;
    private int gender;
    private String firstName;
    private String lastName;
    private int birthYear;
    private String biography;

    public UserPersonalDataDTO() {
    }

    public UserPersonalDataDTO(String email,int gender, String firstName, String lastName, int birthYear,String biography) {
        this.gender = gender;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
        this.biography = biography;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }
}
