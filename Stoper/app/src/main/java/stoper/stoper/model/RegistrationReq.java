package stoper.stoper.model;

public class RegistrationReq {

    private Long id;


    private int gender;

    private String first_name;

    private String lastName;


    private int year_of_birth;

    private String email;

    private String password;

    public RegistrationReq() {
        super();
    }

    public RegistrationReq(int gender, String first_name, String lastName, int year_of_birth, String email, String password) {
        this.gender = gender;
        this.first_name = first_name;
        this.lastName = lastName;
        this.year_of_birth = year_of_birth;
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

    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

}
