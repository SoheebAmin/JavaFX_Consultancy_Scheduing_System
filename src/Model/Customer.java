package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** This is class for customer objects, based on the data required to add a customer record to the database. */
public abstract class Customer {

    private ObservableList<?> allAppointments = FXCollections.observableArrayList();
    private int cID;
    private String name;
    private String address;
    private int postal;
    private String phone;
    private String country;
    private String division;

    public Customer(int cID, String name, String address, int postal, String phone, String country, String division) {
        this.cID = cID;
        this.name = name;
        this.address = address;
        this.postal = postal;
        this.phone = phone;
        this.country = country;
        this.division = division;
    }

    public ObservableList<?> getAllAppointments() {
        return allAppointments;
    }

    public void setAllAppointments(ObservableList<?> allAppointments) {
        this.allAppointments = allAppointments;
    }

    public int getcID() {
        return cID;
    }

    public void setcID(int cID) {
        this.cID = cID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPostal() {
        return postal;
    }

    public void setPostal(int postal) {
        this.postal = postal;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }
}



