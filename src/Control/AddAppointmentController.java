package Control;

import Databse.SelectStatements;
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

/** The Controller to add customer objects to the customers list stored in the ObservableLists class */
public class AddAppointmentController implements Initializable {

    // Variables for the fields to be filled in.
    @FXML
    private TextField idText;
    @FXML private ComboBox customerCB;
    @FXML private TextField titleText;
    @FXML private TextField descriptionText;
    @FXML private TextField locationText;
    @FXML private ComboBox typeCB;
    @FXML private ComboBox contactCB;
    @FXML private ComboBox dateCB;
    @FXML private ComboBox startCB;
    @FXML private ComboBox endCB;

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
            AddAppointmentController.selectedCustomer = customerCB.getSelectionModel().getSelectedItem().toString();
        }
        catch (NullPointerException e) {
            return;
        }
    }

    /** This method sets which type is chosen. */
    public void typeCBSet() {
        // try-catch deals with scenario in which nothing is selected.
        try {
            AddAppointmentController.selectedType = typeCB.getSelectionModel().getSelectedItem().toString();
        }
        catch (NullPointerException e) {
            return;
        }
    }

    /** This method sets which contact is chosen. */
    public void contactCBSet() {
        // try-catch deals with scenario in which nothing is selected.
        try {
            AddAppointmentController.selectedContact = contactCB.getSelectionModel().getSelectedItem().toString();
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
        String name = titleText.getText();
        if (name.equals("")) {
            ControllerMethods.errorDialogueBox("Title Error: Please enter a title");
            errorDetected = true;
        }

        // error check, and then add description
        String address = descriptionText.getText();
        if (address.equals("")) {
            ControllerMethods.errorDialogueBox("Description Error: Please enter a description");
            errorDetected = true;
        }

        // error check, and then add location
        String postal = locationText.getText();
        if (postal.equals("")) {
            ControllerMethods.errorDialogueBox("Postal Code Error: Please enter a location");
            errorDetected = true;
        }

        // check if customer ID is empty. If not, convert to int add the customer
        String customerString = AddAppointmentController.selectedCustomer;
        if(customerString.equals(""))
        {
            ControllerMethods.errorDialogueBox("You must select a customer!");
            errorDetected = true;
        }


        // check if appointment type is empty. If not, add the type
        String type = AddAppointmentController.selectedType;
        if(type.equals(""))
        {
            ControllerMethods.errorDialogueBox("You must select an appointment type!");
            errorDetected = true;
        }

        // check if contact is empty. If not, add contact
        String contact = AddAppointmentController.selectedContact;
        if(contact.equals(""))
        {
            ControllerMethods.errorDialogueBox("You must select a contact!");
            errorDetected = true;
        }

        // check if date is empty. If not, convert to local date and add the date
        String dateString = AddAppointmentController.selectedDate;
        if(dateString.equals(""))
        {
            ControllerMethods.errorDialogueBox("You must select a contact!");
            errorDetected = true;
        }



        // check if start time is empty. If not, convert to local time and add start time contact
        String startString = AddAppointmentController.selectedStart;
        if(startString.equals(""))
        {
            ControllerMethods.errorDialogueBox("You must select a contact!");
            errorDetected = true;
        }


        // check if start end time is empty. If not, convert to local time and add start time contact
        String endString = AddAppointmentController.selectedEnd;
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


        //Calls the insert statement to add the new appointment to the database.

        //InsertStatements.insertCustomer(DBConnection.getConn(), id, name, address, postal, phone, RuntimeObjects.getCurrentUser().getUsername(), division_id);

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

    }
}
