package Control;

import Model.Appointment;
import Model.RuntimeObjects;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


import java.time.LocalDateTime;

public class ReportsController {

    // Variables for labels to display totals
    @FXML private Label totalByType;
    @FXML private Label totalByMonth;
    @FXML private ComboBox<String> typeCB;
    @FXML private ComboBox<String> monthCB;
    @FXML private ComboBox<String> contactCB;


    // Temporary variables to save combo box selection
    private static String selectedType = "";
    private static String selectedMonth = "";
    private static String selectedContact = "";

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
        ObservableList<String> contactCBItems = RuntimeObjects.getAllContacts();

        // sets the list in the combo box
        monthCB.setItems(contactCBItems);
    }


    /** This method populates the contact combo box. */
    public void contactCBSelected() {
        // grab contacts from runtime class where it is stored
        ObservableList<String> contactCBItems = RuntimeObjects.getAllContacts();

        // sets the list in the combo box
        contactCB.setItems(contactCBItems);
    }

    /** This method sets which type is chosen. */
    public void typeCBSet() {
        // try-catch deals with scenario in which nothing is selected.
        try {
            ReportsController.selectedType = typeCB.getSelectionModel().getSelectedItem();
        }
        catch (NullPointerException ignored) {
        }
    }

    /** This method sets which type is chosen. */
    public void monthCBSet() {
        // try-catch deals with scenario in which nothing is selected.
        try {
            ReportsController.selectedMonth = monthCB.getSelectionModel().getSelectedItem();
        }
        catch (NullPointerException ignored) {
        }
    }

    /** This method sets which contact is chosen. */
    public void contactCBSet() {
        // try-catch deals with scenario in which nothing is selected.
        try {
            ReportsController.selectedContact = contactCB.getSelectionModel().getSelectedItem();
        }
        catch (NullPointerException ignored) {
        }
    }



}
