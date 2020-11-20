package Control;

import Databse.DeleteStatements;
import Databse.InsertStatements;
import Databse.SelectStatements;
import Model.Appointment;
import Model.RuntimeObjects;
import Utils.ControllerMethods;
import Utils.DBConnection;
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
    @FXML private ComboBox<String> customerCB;
    @FXML private TextField titleText;
    @FXML private TextField descriptionText;
    @FXML private TextField locationText;
    @FXML private ComboBox<String> typeCB;
    @FXML private ComboBox<String> contactCB;
    @FXML private ComboBox<LocalDate> dateCB;
    @FXML private ComboBox<LocalTime> startCB;
    @FXML private ComboBox<LocalTime> endCB;

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
        titleText.setText(appointment.getTitle());
        descriptionText.setText(appointment.getDescription());
        locationText.setText(appointment.getLocation());
        typeCB.setValue(appointment.getType());

        //Get the customer name using the stored contact ID.
        int customerId = appointment.getCustomerId();
        String selectCustomerString = "SELECT Customer_Name FROM customers WHERE Customer_ID = " + customerId +";";
        String customerName = SelectStatements.getAString(DBConnection.getConn(), selectCustomerString, "Customer_Name");
        customerCB.setValue(customerName);

        //Get the contact name using the stored contact ID.
        int contactId = appointment.getContactId();
        String selectContactString = "SELECT Contact_Name FROM contacts WHERE Contact_ID = " + contactId +";";
        String contactName = SelectStatements.getAString(DBConnection.getConn(), selectContactString, "Contact_Name");
        contactCB.setValue(contactName);

        // grab the date in LocalDate and the start time in LocalTime using the stored start time LocalDateTime.
        LocalDateTime startDateTime = appointment.getStartDateTime();
        LocalDate date = startDateTime.toLocalDate();
        LocalTime start = startDateTime.toLocalTime();
        dateCB.setValue(date);
        startCB.setValue(start);

        // grab the end time LocalTime using the stored end time LocalDateTime.
        LocalDateTime endDateTime = appointment.getEndDateTime();
        LocalTime end = endDateTime.toLocalTime();
        endCB.setValue(end);

        // sets the stored selection for the combo boxes as string versions of the just-retrieved values.
        selectedType = appointment.getType();
        selectedCustomer = customerName;
        selectedContact = contactName;
        selectedDate = date.toString();
        selectedStart = start.toString();
        selectedEnd = end.toString();
    }




    /** This method populates the customer ID combo box. */
    public void customerCBSelected() {
        // calls methods to generate list of customers from the DB with an SQL select
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
            ModifyAppointmentController.selectedCustomer = customerCB.getSelectionModel().getSelectedItem();
        }
        catch (NullPointerException e) {
            return;
        }
    }

    /** This method sets which type is chosen. */
    public void typeCBSet() {
        // try-catch deals with scenario in which nothing is selected.
        try {
            ModifyAppointmentController.selectedType = typeCB.getSelectionModel().getSelectedItem();
        }
        catch (NullPointerException e) {
            return;
        }
    }

    /** This method sets which contact is chosen. */
    public void contactCBSet() {
        // try-catch deals with scenario in which nothing is selected.
        try {
            ModifyAppointmentController.selectedContact = contactCB.getSelectionModel().getSelectedItem();
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

        // grab the id stored in the controller for the current appointment.
        int id = ModifyAppointmentController.currentAppointment;

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

        // check if customer is empty. If not, add the customer
        String customer = ModifyAppointmentController.selectedCustomer;
        if(customer.equals(""))
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
            ControllerMethods.errorDialogueBox("You must select a date!");
            errorDetected = true;
        }

        // check if start time is empty. If not, convert to local time and add start time
        String startString = ModifyAppointmentController.selectedStart;
        if(startString.equals(""))
        {
            ControllerMethods.errorDialogueBox("You must select a start time!");
            errorDetected = true;
        }

        // check if end time is empty. If not, convert to local time and add end time.
        String endString = ModifyAppointmentController.selectedEnd;
        if(endString.equals(""))
        {
            ControllerMethods.errorDialogueBox("You must select an end time!");
            errorDetected = true;
        }

        // return the function if any errors were detected.
        if(errorDetected)
            return false;

        // Conversions to needed data types done once all validation passed
        LocalDate date = LocalDate.parse(dateString);
        LocalTime start = LocalTime.parse(startString);
        LocalTime end = LocalTime.parse(endString);

        // Create the LocalDateTime objects for the appointment start and end time.
        LocalDateTime appointmentStart = LocalDateTime.of(date, start);
        LocalDateTime appointmentEnd = LocalDateTime.of(date, end);

        // gets the customer ID
        String selectCustomerID = "SELECT Customer_ID FROM customers WHERE Customer_Name = \"" + customer + "\"";
        int customerID = SelectStatements.getAnInt(DBConnection.getConn(), selectCustomerID, "Customer_ID");

        // gets the Contact ID
        String selectContactID = "SELECT Contact_ID FROM contacts WHERE Contact_Name = \"" + contact + "\"";
        int contactID = SelectStatements.getAnInt(DBConnection.getConn(), selectContactID, "Contact_ID");

        //grab the created datetime and user
        String LDTSelectStatement = "SELECT Create_Date FROM appointments WHERE Appointment_ID =" + id + ";";
        LocalDateTime Create_Date = SelectStatements.getALocalDateTime(DBConnection.getConn(), LDTSelectStatement, "Create_Date");

        String stringSelectStatement = "SELECT Created_By FROM appointments WHERE Appointment_ID =" + id + ";";
        String Created_By = SelectStatements.getAString(DBConnection.getConn(), stringSelectStatement, "Created_By");

        // Deletes the appointment as it already is in the database.
        String deleteStatement = "DELETE FROM appointments WHERE Appointment_ID =" + id + ";";
        DeleteStatements.delete(DBConnection.getConn(), deleteStatement);

        //Calls the insert statement to add the new appointment to the database.
        InsertStatements.modifyAppointment(DBConnection.getConn(), id, title, description, location, type, appointmentStart, appointmentEnd,
                Create_Date, Created_By, RuntimeObjects.getCurrentUser().getUsername(), customerID, RuntimeObjects.getCurrentUser().getId(), contactID);

        // clear the current customers observable list, and fetch them again from the database.
        RuntimeObjects.clearAllAppointments();
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
