package stoper.stoper.DTO;

public class UserEmailDTO {

    private String oldEmail;
    private String newEmail;
    private Boolean confirmed;

    public UserEmailDTO() {
    }

    public UserEmailDTO(String oldEmail, String newEmail, Boolean confirmed) {
        this.oldEmail = oldEmail;
        this.newEmail = newEmail;
        this.confirmed = confirmed;
    }

    public String getOldEmail() {
        return oldEmail;
    }

    public void setOldEmail(String oldEmail) {
        this.oldEmail = oldEmail;
    }

    public String getNewEmail() {
        return newEmail;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }

    public Boolean getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        this.confirmed = confirmed;
    }
}
