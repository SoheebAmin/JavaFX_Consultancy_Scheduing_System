package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;

public class Appointment {

    // the fields that define the data within every customer object.
    private int aID;
    private String title;
    private String location;;
    private String description;
    private String type;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int cID;
    private int ctID;


    public Appointment(int aID, String title, String location, String description, String type, LocalDateTime startTime, LocalDateTime endTime, int cID, int ctID) {
        this.aID = aID;
        this.title = title;
        this.location = location;
        this.description = description;
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
        this.cID = cID;
        this.ctID = ctID;
    }

    public int getaID() {
        return aID;
    }

    public void setaID(int aID) {
        this.aID = aID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public int getcID() {
        return cID;
    }

    public void setcID(int cID) {
        this.cID = cID;
    }

    public int getCtID() {
        return ctID;
    }

    public void setCtID(int ctID) {
        this.ctID = ctID;
    }
}
