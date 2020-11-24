package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** Customer class that defines what a customer object is for the program. */
public class Customer {

    // list of appointments to keep track of each customer's appointments.
    private ObservableList<?> allAppointments = FXCollections.observableArrayList();

    // the fields that define the data within every customer object.
    private int id;
    private String name;
    private String address;
    private String postal;
    private String phone;
    private String country;
    private String division;

    public Customer(int id, String name, String address, String postal, String phone, String country, String division) {
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setId(int cID) {
        this.id = cID;
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

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
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



