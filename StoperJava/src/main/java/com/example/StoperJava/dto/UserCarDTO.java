package com.example.StoperJava.dto;

public class UserCarDTO {
    private String email;
    
    private int carCountry;

    private int carColor;

    private int carType;

    private String carRegistratonNumber;

    private String carBrand;

    private String carBrandModel;

    private int carYear;

    public UserCarDTO() {
    }

    public UserCarDTO(String email, int carCountry, int carColor, int carType, String carRegistratonNumber, String carBrand, String carBrandModel, int carYear) {
        this.email = email;
        this.carCountry = carCountry;
        this.carColor = carColor;
        this.carType = carType;
        this.carRegistratonNumber = carRegistratonNumber;
        this.carBrand = carBrand;
        this.carBrandModel = carBrandModel;
        this.carYear = carYear;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
