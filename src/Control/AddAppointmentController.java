package Control;

import Databse.InsertStatements;
import Databse.SelectStatements;
import Model.RuntimeObjects;
import Utils.ControllerMethods;
import Utils.DBConnection;
import javafx.application.HostServices;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import jdk.jfr.Description;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

/** The Controller to add appointment objects to the appoint list stored in the ObservableLists class */
public class AddAppointmentController implements Initializable {

    // Variables for the fields to be filled in.
    @FXML private ComboBox<String> customerCB;
    @FXML private TextField titleText;
    @FXML private TextField descriptionText;
    @FXML private TextField locationText;
    @FXML private ComboBox<String> typeCB;
    @FXML private ComboBox<String> contactCB;
    @FXML private ComboBox<LocalDate> dateCB;
    @FXML private ComboBox<LocalTime> startCB;
    @FXML private ComboBox<LocalTime> endCB;

    // To display current user
    @FXML private Label currentUserLabel;

    // To display offset in message
    @FXML private Label offsetMessageLabel;

    // Temporary variables to save combo box selection
    private static String selectedCustomer = "";
    private static String selectedType = "";
    private static String selectedContact = "";
    private static String selectedDate = "";
    private static String selectedStart = "";
    private static String selectedEnd = "";



    /** This method populates the customer ID combo box. */
    public void customerCBSelected() {
        // calls methods to generate list of countries from the DB with an SQL select
        // Observable lists to populate the combo boxes
        ObservableList<String> customerCBItems = SelectStatements.getComboBoxStringList(DBConnection.getConn(), "SELECT Customer_Name FROM customers;", "Customer_Name");

        // sets the list in the combo box
        customerCB.setItems(customerCBItems);
    }

    /** This method populates the type combo box. */
    public void typeCBSelected() {
        // grab types from runtime class where it is stored
        ObservableList<String> typeCBItems = RuntimeObjects.getAllAppointmentTypes();

        // sets the list in the combo box
        typeCB.setItems(typeCBItems);
    }

    /** This method populates the contact combo box. */
    public void contactCBSelected() {
        // grab contacts from runtime class where it is stored
        ObservableList<String> contactCBItems = RuntimeObjects.getAllContacts();

        // sets the list in the combo box
        contactCB.setItems(contactCBItems);
    }

    /** This method populates the date ID combo box. */
    public void dateCBSelected() {
        // grabs dates from runtime class where it is stored
        ObservableList<LocalDate> dateCBItems = RuntimeObjects.getAllAppointmentDates();

        // sets the list in the combo box
        dateCB.setItems(dateCBItems);
    }

    /** This method populates the start and end time combo boxes. */
    public void timeCBSelected() {
        // grabs time time intervals from runtime class where it is stored

        ObservableList<LocalTime> timeCBItems = RuntimeObjects.getAllAppointmentHours();

        // sets the list in the start and end time combo boxes
        startCB.setItems(timeCBItems);
        endCB.setItems(timeCBItems);
    }


    
    /** This method sets which customer is chosen. */
    public void customerCBSet() {
        // try-catch deals with scenario in which nothing is selected.
        try {
            AddAppointmentController.selectedCustomer = customerCB.getSelectionModel().getSelectedItem();
        }
        catch (NullPointerException e) {
            return;
        }
    }

    /** This method sets which type is chosen. */
    public void typeCBSet() {
        // try-catch deals with scenario in which nothing is selected.
        try {
            AddAppointmentController.selectedType = typeCB.getSelectionModel().getSelectedItem();
        }
        catch (NullPointerException e) {
            return;
        }
    }

    /** This method sets which contact is chosen. */
    public void contactCBSet() {
        // try-catch deals with scenario in which nothing is selected.
        try {
            AddAppointmentController.selectedContact = contactCB.getSelectionModel().getSelectedItem();
        }
        catch (NullPointerException e) {
            return;
        }
    }

    /** This method sets which customer is chosen. */
    public void dateCBSet() {
        // try-catch deals with scenario in which nothing is selected.
        try {
            AddAppointmentController.selectedDate = dateCB.getSelectionModel().getSelectedItem().toString();
        }
        catch (NullPointerException e) {
            return;
        }
    }

    /** This method sets which customer is chosen. */
    public void startCBSet() {
        // try-catch deals with scenario in which nothing is selected.
        try {
            AddAppointmentController.selectedStart = startCB.getSelectionModel().getSelectedItem().toString();
        }
        catch (NullPointerException e) {
            return;
        }
    }

    /** This method sets which customer is chosen. */
    public void endCBSet() {
        // try-catch deals with scenario in which nothing is selected.
        try {
            AddAppointmentController.selectedEnd = endCB.getSelectionModel().getSelectedItem().toString();
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
        String title = "";
        title = titleText.getText();
        if (title.equals("")) {
            ControllerMethods.errorDialogueBox("Title Error: Please enter a title");
            errorDetected = true;
        }

        // error check, and then add description
        String description = "";
        description = descriptionText.getText();
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

        // check if customer is empty. If not, add the customer
        String customer = "";
        customer = AddAppointmentController.selectedCustomer;
        if(customer.equals(""))
        {
            ControllerMethods.errorDialogueBox("You must select a customer!");
            errorDetected = true;
        }

        // check if appointment type is empty. If not, add the type
        String type = "";
        type = AddAppointmentController.selectedType;
        if(type.equals(""))
        {
            ControllerMethods.errorDialogueBox("You must select an appointment type!");
            errorDetected = true;
        }

        // check if contact is empty. If not, add contact
        String contact = "";
        contact = AddAppointmentController.selectedContact;
        if(contact.equals(""))
        {
            ControllerMethods.errorDialogueBox("You must select a contact!");
            errorDetected = true;
        }

        // check if date is empty. If not, convert to local date and add the date
        String dateString = "";
        dateString = AddAppointmentController.selectedDate;
        if(dateString.equals(""))
        {
            ControllerMethods.errorDialogueBox("You must select a date!");
            errorDetected = true;
        }


        // check if start time is empty. If not, convert to local time and add start time
        String startString = "";
        startString = AddAppointmentController.selectedStart;
        if(startString.equals(""))
        {
            ControllerMethods.errorDialogueBox("You must select a start time!");
            errorDetected = true;
        }

        // check if end time is empty. If not, convert to local time and add end time
        String endString = "";
        endString = AddAppointmentController.selectedEnd;
        if(endString.equals(""))
        {
            ControllerMethods.errorDialogueBox("You must select an end time!");
            errorDetected = true;
        }

        // return the function if any errors were detected.
        if(errorDetected)
            return false;

        // Conversions to needed data types done once all validation passed
        LocalDate startDate = LocalDate.parse(dateString);
        LocalDate endDate = LocalDate.parse(dateString);
        LocalTime start = LocalTime.parse(startString);
        LocalTime end = LocalTime.parse(endString);


        // check if times are in order
        if(start.isAfter(end))
        {
            // checks to see if local hours go over midnight hours, which would mean we should allow this with an additional day added.
            if(RuntimeObjects.isComplexHours() == false) {
            ControllerMethods.errorDialogueBox("Your start time cannot be after your end time!");
            errorDetected = true;
            }
            else
            {
                endDate = endDate.plusDays(1);
            }
        }

        // return the function if any errors were detected.
        if(errorDetected)
            return false;

        // Create the LocalDateTime objects for the appointment start and end time.
        LocalDateTime appointmentStart = LocalDateTime.of(startDate, start);
        LocalDateTime appointmentEnd = LocalDateTime.of(endDate, end);

        // gets the customer ID
        String selectCustomerID = "SELECT Customer_ID FROM customers WHERE Customer_Name = \"" + customer + "\"";
        int customerID = SelectStatements.getAnInt(DBConnection.getConn(), selectCustomerID, "Customer_ID");

        // gets the Contact ID
        String selectContactID = "SELECT Contact_ID FROM contacts WHERE Contact_Name = \"" + contact + "\"";
        int contactID = SelectStatements.getAnInt(DBConnection.getConn(), selectContactID, "Contact_ID");

        //Calls the insert statement to add the new appointment to the database.
        InsertStatements.insertAppointment(DBConnection.getConn(), id, title, description, location, type, appointmentStart, appointmentEnd,
                RuntimeObjects.getCurrentUser().getUsername(), customerID, RuntimeObjects.getCurrentUser().getId(), contactID);

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
        AddAppointmentController.selectedCustomer = "";
        AddAppointmentController.selectedType = "";
        AddAppointmentController.selectedContact = "";
        AddAppointmentController.selectedDate = "";
        AddAppointmentController.selectedStart = "";
        AddAppointmentController.selectedEnd = "";
    }

    /** Method to set initial conditions of the controller. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // grabs and sets the current user
        currentUserLabel.setText(RuntimeObjects.getCurrentUser().getUsername());

        // grab the offset
        int offset = RuntimeObjects.getOffset();

        // based on offset, sets message on hours change for user.
        if(offset > 0)
        {
            String message = "Office hours are in EST, which your timezone is ahead of by " + offset + " hours.";
            offsetMessageLabel.setText(message);

        }
        if(offset < 0)
        {
            String message = "Office hours are in EST, which your timezone is behind by " + Math.abs(offset) + " hours.";
            offsetMessageLabel.setText(message);
        }
       if(offset == 0)
        {
            offsetMessageLabel.setText("Office hours are in EST, which is the same as your timezone.");
        }

    }
}
