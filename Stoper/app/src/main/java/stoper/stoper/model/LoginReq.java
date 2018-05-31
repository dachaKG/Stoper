package stoper.stoper.model;

public class LoginReq {

    private String email;

    private String password;


    public LoginReq() {
        super();

    }


    public LoginReq(String email, String password) {
        super();
        this.email = email;
        this.password = password;
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


}
