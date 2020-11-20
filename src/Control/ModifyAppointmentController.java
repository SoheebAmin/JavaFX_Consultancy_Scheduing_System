package Control;

import Databse.InsertStatements;
import Databse.SelectStatements;
import Model.Appointment;
import Model.RuntimeObjects;
import Utils.ControllerMethods;
import Utils.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

/** The Controller to add appointment objects to the appoint list stored in the ObservableLists class */
public class ModifyAppointmentController implements Initializable {

    // Variables for the fields to be filled in.
    @FXML private TextField idText;
    @FXML private ComboBox<Integer> customerCB;
    @FXML private TextField titleText;
    @FXML private TextField descriptionText;
    @FXML private TextField locationText;
    @FXML private ComboBox<String> typeCB;
    @FXML private ComboBox<String> contactCB;
    @FXML private ComboBox<LocalDate> dateCB;
    @FXML private ComboBox<LocalTime> startCB;
    @FXML private ComboBox<LocalTime> endCB;

    // Observable lists to populate the combo boxes
    private static ObservableList<Integer> customerCBItems = FXCollections.observableArrayList();
    private static ObservableList<String> typeCBItems = FXCollections.observableArrayList();
    private static ObservableList<String> contactCBItems = FXCollections.observableArrayList();
    private static ObservableList<LocalDate> dateCBItems = FXCollections.observableArrayList();
    private static ObservableList<LocalTime> timeCBItems = FXCollections.observableArrayList();

    // Temporary variables to save combo box selection
    private static String selectedCustomer = "";
    private static String selectedType = "";
    private static String selectedContact = "";
    private static String selectedDate = "";
    private static String selectedStart = "";
    private static String selectedEnd = "";
    // ID of current customer being modified

    private static int currentAppointment;


    /** This method allows the Customer Dashboard to send the data of the selected customer to the created controller object. */
    public void setAppointmentInfo(Appointment appointment) {

        // sets the current id being worked on
        ModifyAppointmentController.currentAppointment = appointment.getId();

        // sends all the data to the text fields and combo boxes.
        idText.setText((String.valueOf((appointment.getId()))));
        customerCB.setValue(appointment.getCustomerId());
        titleText.setText(appointment.getTitle());
        descriptionText.setText(appointment.getDescription());
        locationText.setText(appointment.getLocation());
        typeCB.setValue(appointment.getType());

        //Get the contact name using the stored contact ID.
        int contactId = appointment.getContactId();
        String selectString = "SELECT Contact_Name FROM contacts WHERE Contact_ID = " + contactId +";";
        String contactName = SelectStatements.getAString(DBConnection.getConn(), selectString, "Contact_Name");
        contactCB.setValue(contactName);


        //dateCB.setValue();
        //startCB.setValue(appointment.getStartTime()); // need to grab only time without date
        //endCB.setValue(appointment.getEndTime()); // need to grab time without date.


        // NOTE: All the below have to be strings. Don't worry, the save button will convert them to whatever they need to be.
        // sets the customer object values in temp vars to be used by the combo boxes.

        //NEED TO CONVERT TO STRING.
        //selectedCustomer = appointment.getCustomerId();


        // Need to grab contact name using contact ID. SQL here probably.
        //selectedContact = appointment.getContactId();

        // Need to split up either start or end and grab date from it.
        //selectedDate = ...

        // sets the stored selection for the combo boxes as string versions of the just-retrieved values.
        selectedType = appointment.getType();
        selectedContact = contactName;
        selectedStart = appointment.getStartTime().toString();
        selectedEnd = appointment.getEndTime().toString();
    }




    /** This method populates the customer ID combo box. */
    public void customerCBSelected() {
        // calls methods to generate list of countries from the DB with an SQL select
        customerCBItems = SelectStatements.getComboBoxIntList(DBConnection.getConn(), "SELECT Customer_ID FROM customers;", "Customer_ID");

        // sets the list in the combo box
        customerCB.setItems(customerCBItems);
    }

    /** This method populates the type combo box. */
    public void typeCBSelected() {
        // grab types from runtime class where it is stored
        typeCBItems = RuntimeObjects.getAllAppointmentTypes();

        // sets the list in the combo box
        typeCB.setItems(typeCBItems);
    }

    /** This method populates the contact combo box. */
    public void contactCBSelected() {
        // grab contacts from runtime class where it is stored
        contactCBItems = RuntimeObjects.getAllContacts();

        // sets the list in the combo box
        contactCB.setItems(contactCBItems);
    }

    /** This method populates the date ID combo box. */
    public void dateCBSelected() {
        // grabs dates from runtime class where it is stored
        dateCBItems = RuntimeObjects.getAllAppointmentDates();

        // sets the list in the combo box
        dateCB.setItems(dateCBItems);
    }

    /** This method populates the start and end time combo boxes. */
    public void timeCBSelected() {
        // grabs time time intervals from runtime class where it is stored

        timeCBItems = RuntimeObjects.getAllAppointmentHours();

        // sets the list in the start and end time combo boxes
        startCB.setItems(timeCBItems);
        endCB.setItems(timeCBItems);
    }

    /** This method sets which customer is chosen. */
    public void customerCBSet() {
        // try-catch deals with scenario in which nothing is selected.
        try {
            ModifyAppointmentController.selectedCustomer = customerCB.getSelectionModel().getSelectedItem().toString();
        }
        catch (NullPointerException e) {
            return;
        }
    }

    /** This method sets which type is chosen. */
    public void typeCBSet() {
        // try-catch deals with scenario in which nothing is selected.
        try {
            ModifyAppointmentController.selectedType = typeCB.getSelectionModel().getSelectedItem().toString();
        }
        catch (NullPointerException e) {
            return;
        }
    }

    /** This method sets which contact is chosen. */
    public void contactCBSet() {
        // try-catch deals with scenario in which nothing is selected.
        try {
            ModifyAppointmentController.selectedContact = contactCB.getSelectionModel().getSelectedItem().toString();
        }
        catch (NullPointerException e) {
            return;
        }
    }

    /** This method sets which customer is chosen. */
    public void dateCBSet() {
        // try-catch deals with scenario in which nothing is selected.
        try {
            ModifyAppointmentController.selectedDate = dateCB.getSelectionModel().getSelectedItem().toString();
        }
        catch (NullPointerException e) {
            return;
        }
    }

    /** This method sets which customer is chosen. */
    public void startCBSet() {
        // try-catch deals with scenario in which nothing is selected.
        try {
            ModifyAppointmentController.selectedStart = startCB.getSelectionModel().getSelectedItem().toString();
        }
        catch (NullPointerException e) {
            return;
        }
    }

    /** This method sets which customer is chosen. */
    public void endCBSet() {
        // try-catch deals with scenario in which nothing is selected.
        try {
            ModifyAppointmentController.selectedEnd = endCB.getSelectionModel().getSelectedItem().toString();
        }
        catch (NullPointerException e) {
            return;
        }
    }

    /** After validating the entries, this methods adds a new record into the database and refreshes it. */
    public boolean saveButtonClicked(ActionEvent event) throws IOException, SQLException {

        boolean errorDetected = false; // boolean to mark if we will abort after all error messages are shown.

        // grab the auto-Id by checking the max ID in the DB and adding 1 to it.
        int id = SelectStatements.getAnInt(DBConnection.getConn(), "SELECT max(Appointment_ID)+1 AS Appointment_ID FROM appointments;", "Appointment_ID");

        // error check and then add title
        String title = titleText.getText();
        if (title.equals("")) {
            ControllerMethods.errorDialogueBox("Title Error: Please enter a title");
            errorDetected = true;
        }

        // error check, and then add description
        String description = descriptionText.getText();
        if (description.equals("")) {
            ControllerMethods.errorDialogueBox("Description Error: Please enter a description");
            errorDetected = true;
        }

        // error check, and then add location
        String location = locationText.getText();
        if (location.equals("")) {
            ControllerMethods.errorDialogueBox("Postal Code Error: Please enter a location");
            errorDetected = true;
        }

        // check if customer ID is empty. If not, convert to int add the customer
        String customerString = ModifyAppointmentController.selectedCustomer;
        if(customerString.equals(""))
        {
            ControllerMethods.errorDialogueBox("You must select a customer!");
            errorDetected = true;
        }

        // check if appointment type is empty. If not, add the type
        String type = ModifyAppointmentController.selectedType;
        if(type.equals(""))
        {
            ControllerMethods.errorDialogueBox("You must select an appointment type!");
            errorDetected = true;
        }

        // check if contact is empty. If not, add contact
        String contact = ModifyAppointmentController.selectedContact;
        if(contact.equals(""))
        {
            ControllerMethods.errorDialogueBox("You must select a contact!");
            errorDetected = true;
        }

        // check if date is empty. If not, convert to local date and add the date
        String dateString = ModifyAppointmentController.selectedDate;
        if(dateString.equals(""))
        {
            ControllerMethods.errorDialogueBox("You must select a contact!");
            errorDetected = true;
        }



        // check if start time is empty. If not, convert to local time and add start time contact
        String startString = ModifyAppointmentController.selectedStart;
        if(startString.equals(""))
        {
            ControllerMethods.errorDialogueBox("You must select a contact!");
            errorDetected = true;
        }


        // check if start end time is empty. If not, convert to local time and add start time contact
        String endString = ModifyAppointmentController.selectedEnd;
        if(endString.equals(""))
        {
            ControllerMethods.errorDialogueBox("You must select a contact!");
            errorDetected = true;
        }

        // return the function if any errors were detected.
        if(errorDetected == true)
            return false;

        // Conversions to needed data types done once all validation passed
        int customer = Integer.parseInt(customerString);
        LocalDate date = LocalDate.parse(dateString);
        LocalTime start = LocalTime.parse(startString);
        LocalTime end = LocalTime.parse(endString);

        // Create the LocalDateTime objects for the appointment start and end time.
        LocalDateTime appointmentStart = LocalDateTime.of(date, start);
        LocalDateTime appointmentEnd = LocalDateTime.of(date, end);

        // gets the Contact ID
        String selectContactID = "SELECT Contact_ID FROM contacts WHERE Contact_Name = \"" + contact + "\"";
        int contactID = SelectStatements.getAnInt(DBConnection.getConn(), selectContactID, "Contact_ID");

        //Calls the insert statement to add the new appointment to the database.
        InsertStatements.insertAppointment(DBConnection.getConn(), id, title, description, location, type, appointmentStart, appointmentEnd,
                RuntimeObjects.getCurrentUser().getUsername(), customer, RuntimeObjects.getCurrentUser().getId(), contactID);

        // clear the current customers observable list, and fetch them again from the database.
        RuntimeObjects.clearAllAppointments();;
        Connection conn = DBConnection.getConn();
        SelectStatements.populateAppointmentsTable(conn);

        // clears combo box temp data variables for future use
        clearComboBoxTempVars();

        ControllerMethods.changeScene(event, "../View/AppointmentDashboard.fxml");
        return true;
    }

    /** This method returns to the MainScreenController without making any changes to the Inventory class. */
    public void cancelButtonClicked(ActionEvent event) throws IOException {
        clearComboBoxTempVars();
        ControllerMethods.changeScene(event, "../View/AppointmentDashboard.fxml");
    }

    /** Resets all the temporary variables that hold selected combo box values. */
    public void clearComboBoxTempVars() {
        ModifyAppointmentController.selectedCustomer = "";
        ModifyAppointmentController.selectedType = "";
        ModifyAppointmentController.selectedContact = "";
        ModifyAppointmentController.selectedDate = "";
        ModifyAppointmentController.selectedStart = "";
        ModifyAppointmentController.selectedEnd = "";
    }

    /** Method to set initial conditions of the controller. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
