package stoper.stoper.model;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Ride implements android.os.Parcelable, Serializable {

    private Long id;

    private String startDestination;

    private String endDestination;

    private String rideDate;

    private Integer passengerNumber;

    private Integer price;

    private String note;

    private int mData;

    public int describeContents() {
         return 0;
     }

    private Integer maxPassengerNum;

    private User driver;

    private Set<User> passengers;

    public void writeToParcel(android.os.Parcel out, int flags) {
         out.writeInt(mData);
     }

    public static final android.os.Parcelable.Creator<Ride> CREATOR
             = new android.os.Parcelable.Creator<Ride>() {
         public Ride createFromParcel(android.os.Parcel in) {
             return new Ride(in);
         }

         public Ride[] newArray(int size) {
             return new Ride[size];
         }
    };
    private Ride(android.os.Parcel in) {
         mData = in.readInt();
    }

    public Ride(){
        passengers = new HashSet<User>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStartDestination() {
        return startDestination;
    }

    public void setStartDestination(String startDestination) {
        this.startDestination = startDestination;
    }

    public String getEndDestination() {
        return endDestination;
    }

    public void setEndDestination(String endDestination) {
        this.endDestination = endDestination;
    }

    public String getRideDate() {
        return rideDate;
    }

    public void setRideDate(String rideDate) {
        this.rideDate = rideDate;
    }

    public Integer getPassengerNumber() {
        return passengerNumber;
    }

    public void setPassengerNumber(Integer passengerNumber) {
        this.passengerNumber = passengerNumber;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getMaxPassengerNum() {
        return maxPassengerNum;
    }

    public void setMaxPassengerNum(Integer maxPassengerNum) {
        this.maxPassengerNum = maxPassengerNum;
    }

    public User getDriver() {
        return driver;
    }

    public void setDriver(User driver) {
        this.driver = driver;
    }

    public Set<User> getPassengers() {
        return passengers;
    }

    public void setPassengers(Set<User> passengers) {
        this.passengers = passengers;
    }

    public boolean addPassenger(User passenger) {
        if(passengers == null) {
            passengers = new HashSet<User>();
        }
        return passengers.add(passenger);
    }
}
