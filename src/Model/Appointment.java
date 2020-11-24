package Model;

import java.time.LocalDateTime;

/** Appointment class that defines what an appointment object is for the program. */
public class Appointment {

    // the fields that define the data within every customer object.
    private int id;
    private String title;
    private String location;
    private String description;
    private String type;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private int customerId;
    private int contactId;
    private int userId;


    public Appointment(int id, String title, String location, String description, String type, LocalDateTime startTime, LocalDateTime endTime, int customerId, int contactId, int userId) {
        this.id = id;
        this.title = title;
        this.location = location;
        this.description = description;
        this.type = type;
        this.startDateTime = startTime;
        this.endDateTime = endTime;
        this.customerId = customerId;
        this.contactId = contactId;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int aID) {
        this.id = aID;
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

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
