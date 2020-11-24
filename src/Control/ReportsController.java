package Control;

import Database.SelectStatements;
import LambdaInterfaces.GrabIntFromDB;
import LambdaInterfaces.RepopulateAppointments;
import Model.Appointment;
import Model.RuntimeObjects;
import Utils.ControllerMethods;
import Utils.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ResourceBundle;
import java.util.TimeZone;

public class ReportsController implements Initializable {

    // Variables for labels to display totals
    @FXML private Label totalByType;
    @FXML private Label totalByMonth;
    @FXML private ComboBox<String> typeCB;
    @FXML private ComboBox<Integer> monthCB;
    @FXML private ComboBox<String> contactCB;
    @FXML private ComboBox<ZoneId> zoneIdCB;


    // Temporary variables to save combo box selection
    private static String selectedType;
    private static int selectedMonth;
    private static String selectedContact;
    private static ZoneId selectedZoneId;

    // Variables for the Customer Table tableview and columns for report 2.
    @FXML private TableView<Appointment> appointmentTableView2;
    @FXML private TableColumn<Appointment, Integer> idCol2;
    @FXML private TableColumn<Appointment, String>  titleCol2;
    @FXML private TableColumn<Appointment, String> locationCol2;
    @FXML private TableColumn<Appointment, String> descriptionCol2;
    @FXML private TableColumn<Appointment, String> typeCol2;
    @FXML private TableColumn<Appointment, LocalDateTime> startTimeCol2;
    @FXML private TableColumn<Appointment, LocalDateTime> endTimeCol2;
    @FXML private TableColumn<Appointment, Integer> customerIdCol2;

    // Variables for the Customer Table tableview and columns for report 3.
    @FXML private TableView<Appointment> appointmentTableView3;
    @FXML private TableColumn<Appointment, Integer> idCol3;
    @FXML private TableColumn<Appointment, String>  titleCol3;
    @FXML private TableColumn<Appointment, String> locationCol3;
    @FXML private TableColumn<Appointment, String> descriptionCol3;
    @FXML private TableColumn<Appointment, String> typeCol3;
    @FXML private TableColumn<Appointment, LocalDateTime> startTimeCol3;
    @FXML private TableColumn<Appointment, LocalDateTime> endTimeCol3;
    @FXML private TableColumn<Appointment, Integer> customerIdCol3;
    @FXML private TableColumn<Appointment, Integer> contactIdCol3;

    /** This method populates the type combo box. */
    public void typeCBSelected() {
        // grab types from runtime class where it is stored
        ObservableList<String> typeCBItems = RuntimeObjects.getAllAppointmentTypes();

        // sets the list in the combo box
        typeCB.setItems(typeCBItems);
    }

    /** This method populates the month combo box. */
    public void monthCBSelected() {
        // grab contacts from runtime class where it is stored
        String selectStatement = "SELECT Month(Start) AS Month FROM appointments GROUP BY Month(Start);";
        ObservableList<Integer> monthCBItems = SelectStatements.getIntList(DBConnection.getConn(), selectStatement, "Month");

        // sets the list in the combo box
        monthCB.setItems(monthCBItems);
    }


    /** This method populates the contact combo box. */
    public void contactCBSelected() {
        // grab contacts from runtime class where it is stored
        ObservableList<String> contactCBItems = RuntimeObjects.getAllContacts();

        // sets the list in the combo box
        contactCB.setItems(contactCBItems);
    }

    /** This method populates the time zones combo box. */
    public void zoneCBSelected() {
        // grab contacts from runtime class where it is stored
        ObservableList<ZoneId> zoneCBItems = RuntimeObjects.getAllUSZoneIds();

        // sets the list in the combo box
        zoneIdCB.setItems(zoneCBItems);
    }


    /** This method sets which type is chosen, and then shows the total appointments by that type. */
    public void typeCBSet() {
        // try-catch deals with scenario in which nothing is selected.
        try {
            ReportsController.selectedType = typeCB.getSelectionModel().getSelectedItem();
            String selectStatement = "SELECT COUNT(*) AS Total FROM appointments WHERE Type  = \"" + ReportsController.selectedType + "\"";
            int totalOfSelectedType = SelectStatements.getAnInt(DBConnection.getConn(),selectStatement, "Total");
            totalByType.setText("Total Appointments: " + totalOfSelectedType);
        }
        catch (NullPointerException ignored) {
        }
    }

    /** This method sets which month is chosen, and then shows the total appointments by that type.. */
    public void monthCBSet() {
        // try-catch deals with scenario in which nothing is selected.
        try {
            ReportsController.selectedMonth = monthCB.getSelectionModel().getSelectedItem();

            String selectStatement = "SELECT Count(*) AS Appointments FROM appointments WHERE Month(Start) = " + ReportsController.selectedMonth + ";";

            //int totalOfSelectedMonth = SelectStatements.getAnInt(DBConnection.getConn(), selectStatement, "Appointments");

            /** LAMBDA EXPRESSION: This is a lambda expression which allows us to grab any integers from the database with the help of a select method in the DB package.*/
            GrabIntFromDB intGrabber = (a, b, c) -> SelectStatements.getAnInt(a, b, c);
            int totalOfSelectedMonth = intGrabber.getAnInt(DBConnection.getConn(), selectStatement, "Appointments");
            totalByMonth.setText("Total Appointments: " + totalOfSelectedMonth);
        }
        catch (NullPointerException ignored) {
        }
    }

    /** This method sets which contact is chosen. and shows all appointments with them. */
    public void contactCBSet() {
        // try-catch deals with scenario in which nothing is selected.
        try {
            ReportsController.selectedContact = contactCB.getSelectionModel().getSelectedItem();

            // get the corresponding contact ID for the selected contact.
            String selectContact = "SELECT Contact_ID FROM contacts WHERE Contact_Name = \"" + ReportsController.selectedContact + "\"";
            int selectedContactID = SelectStatements.getAnInt(DBConnection.getConn(), selectContact, "Contact_ID");

            // grabs all appointments on record
            ObservableList<Appointment> allAppointments = RuntimeObjects.getAllAppointments();

            // a list to hold all appointments with this contact
            ObservableList<Appointment> matchingAppointments = FXCollections.observableArrayList();

            // loops through all appointments, and checks which one have this contact.
            for (Appointment a : allAppointments) {
                int contactID = a.getContactId();
                if(selectedContactID == contactID)
                    matchingAppointments.add(a);
            }
            appointmentTableView2.setItems(matchingAppointments);
        }
        catch (NullPointerException ignored) {
        }
    }
    /** This method sets which zoneID is chosen. */
    public void zoneCBSet() {
        // try-catch deals with scenario in which nothing is selected.
        try {
            ReportsController.selectedZoneId = zoneIdCB.getSelectionModel().getSelectedItem();

            // sets the current zone ID for the program to the one the user selected. This is restored upon exit.
            TimeZone.setDefault(TimeZone.getTimeZone(selectedZoneId));

            // clear the current appointments observable list, and fetch them again from the database.

            /** LAMBDA EXPRESSION: This is a lambda expression which allows us to repopulate the appointment table to reflect the timezone changes.*/
            RepopulateAppointments repopulator = c -> {
                RuntimeObjects.clearAllAppointments();
                SelectStatements.populateAppointmentsTable(c);};

            repopulator.repopulateDB(DBConnection.getConn());
        }
        catch (NullPointerException ignored) {
        }
    }

    /** This method exits the program via the Exit button */
    public void exitButtonClicked(ActionEvent event) throws IOException {

        // Restores the default time zone stored at the start of the program.
        TimeZone.setDefault(RuntimeObjects.getCurrentTimeZone());
        RuntimeObjects.clearAllAppointments();
        Connection conn = DBConnection.getConn();
        SelectStatements.populateAppointmentsTable(conn);

        ControllerMethods.changeScene(event, "../View/CustomerDashboard.fxml");
    }

    /** Resets all the temporary variables that hold selected combo box values. */
    public void clearAllTempVars() {
        ReportsController.selectedType = "";
        ReportsController.selectedContact = "";
        ReportsController.selectedMonth = LocalDateTime.now().getMonthValue();
        ReportsController.selectedZoneId = null;
    }

    /** Method to set initial conditions of the controller. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // to populate the customer table for report 2, once contact is chosen.

        idCol2.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleCol2.setCellValueFactory(new PropertyValueFactory<>("title"));
        locationCol2.setCellValueFactory(new PropertyValueFactory<>("location"));
        descriptionCol2.setCellValueFactory(new PropertyValueFactory<>("description"));
        typeCol2.setCellValueFactory(new PropertyValueFactory<>("type"));
        startTimeCol2.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
        endTimeCol2.setCellValueFactory(new PropertyValueFactory<>("endDateTime"));
        customerIdCol2.setCellValueFactory(new PropertyValueFactory<>("customerId"));


        // to populate the customer table for report 3
        appointmentTableView3.setItems(RuntimeObjects.getAllAppointments());

        idCol3.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleCol3.setCellValueFactory(new PropertyValueFactory<>("title"));
        locationCol3.setCellValueFactory(new PropertyValueFactory<>("location"));
        descriptionCol3.setCellValueFactory(new PropertyValueFactory<>("description"));
        typeCol3.setCellValueFactory(new PropertyValueFactory<>("type"));
        startTimeCol3.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
        endTimeCol3.setCellValueFactory(new PropertyValueFactory<>("endDateTime"));
        customerIdCol3.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        contactIdCol3.setCellValueFactory(new PropertyValueFactory<>("contactId"));
    }
}
