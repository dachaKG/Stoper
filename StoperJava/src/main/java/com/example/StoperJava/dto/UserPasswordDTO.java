package com.example.StoperJava.dto;

public class UserPasswordDTO {

    private String email;
    private String oldPassword;
    private String newPassword;
    private String confirmedNewPassword;

    public UserPasswordDTO() {
    }

    public UserPasswordDTO(String email, String oldPassword, String newPassword, String confirmedNewPassword) {
        this.email = email;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.confirmedNewPassword = confirmedNewPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmedNewPassword() {
        return confirmedNewPassword;
    }

    public void setConfirmedNewPassword(String confirmedNewPassword) {
        this.confirmedNewPassword = confirmedNewPassword;
    }
}