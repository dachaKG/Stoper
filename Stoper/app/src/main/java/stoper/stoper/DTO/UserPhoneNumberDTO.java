package stoper.stoper.DTO;

public class UserPhoneNumberDTO {

    private String email;
    private String areaCall;
    private String phoneNumber;

    public UserPhoneNumberDTO() {
    }

    public UserPhoneNumberDTO(String email, String areaCall, String phoneNumber) {
        this.email = email;
        this.areaCall = areaCall;
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
