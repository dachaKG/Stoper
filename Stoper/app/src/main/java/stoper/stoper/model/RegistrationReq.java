package stoper.stoper.model;

public class RegistrationReq {

    private Long id;


    private int gender;

    private String firstName;

    private String lastName;


    private int yearOfBirth;

    private String email;

    private String password;

    public RegistrationReq() {
        super();
    }

    public RegistrationReq(int gender, String firstName, String lastName, int yearOfBirth, String email, String password) {
        this.gender = gender;
        this.firstName = firstName;
        this.lastName = lastName;
        this.yearOfBirth = yearOfBirth;
        this.email = email;
        this.password = password;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getFirst_name() {
        return firstName;
    }

    public void setfirst_name(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getyear_of_birth() {
        return yearOfBirth;
    }

    public void setyear_of_birth(int yearOfBirth) {
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

    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

}
