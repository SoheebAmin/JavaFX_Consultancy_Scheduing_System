package Control;

import Model.Appointment;
import Model.RuntimeObjects;
import Utils.ControllerMethods;
import Utils.DBConnection;
import Database.DeleteStatements;
import Utils.DateTimeMethods;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;

/** This is the controller class for the Appointments Dashboard.*/
public class AppointmentDashboardController implements Initializable {

    // Variables for the Customer Table tableview and columns.
    @FXML private TableView<Appointment> appointmentTableView;
    @FXML private TableColumn<Appointment, Integer> idCol;
    @FXML private TableColumn<Appointment, String>  titleCol;
    @FXML private TableColumn<Appointment, String> locationCol;
    @FXML private TableColumn<Appointment, String> descriptionCol;
    @FXML private TableColumn<Appointment, String> typeCol;
    @FXML private TableColumn<Appointment, LocalDateTime> startTimeCol;
    @FXML private TableColumn<Appointment, LocalDateTime> endTimeCol;
    @FXML private TableColumn<Appointment, Integer> customerIdCol;
    @FXML private TableColumn<Appointment, Integer> contactIdCol;
    @FXML private TableColumn<Appointment, Integer> userIdCol;
    @FXML private RadioButton allRadioButton;


    /** This method allows the user to add an appointment */
    public void addButtonClicked(ActionEvent event) throws IOException {
        ControllerMethods.changeScene(event, "../View/AddAppointment.fxml");
    }

    /** This method allows the user to modify an appointment */
    public void modifyButtonClicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../View/modifyAppointment.fxml"));
        loader.load();

        // Send the data selected from the table view to the Modify Part Menu.
        ModifyAppointmentController MAC = loader.getController();
        try
        {
            Appointment selectedAppointment = appointmentTableView.getSelectionModel().getSelectedItem();
            MAC.setAppointmentInfo(selectedAppointment);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("You need to select an appointment first!");
            alert.showAndWait();
            return;
        }

        // Creates new scene.
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        window.setScene(new Scene(scene));
        window.show();
    }

    /** Method to delete a selected appointment.*/
    public void deleteButtonClicked() {
        // grabs selected appointment
        Appointment selectedAppointment = appointmentTableView.getSelectionModel().getSelectedItem();

        // abort function if null
        if(selectedAppointment == null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("You need to select an appointment to delete!");
            alert.showAndWait();
            return;
        }
        int appointmentId = selectedAppointment.getId();
        String appointmentType = selectedAppointment.getType();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete " + appointmentType + " with A.ID " + appointmentId + "?");

        Optional<ButtonType> result = alert.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK) {

            // prepares the SQL Delete statement
            String SQLStatement = "DELETE FROM appointments WHERE Appointment_ID =" + appointmentId + ";";

            // deletes the record from the database itself
            DeleteStatements.delete(DBConnection.getConn(), SQLStatement);

            // then deletes it from table view, and refreshes it.
            RuntimeObjects.deleteAppointment(selectedAppointment);
            appointmentTableView.setItems(RuntimeObjects.getAllAppointments());
        }
    }

    /** This method is triggered when "all" radio button is selected. It shows all appointments.*/
    public void allRadioButtonSelected(){
        appointmentTableView.setItems(RuntimeObjects.getAllAppointments());
    }

    /** This method is triggered when "week" radio button is selected. It shows the appointments for the next 7 days.*/
    public void weekRadioButtonSelected() {

        // grabs all appointments on record
        ObservableList<Appointment> allAppointments = RuntimeObjects.getAllAppointments();

        // gets a list of future dates
        ObservableList<LocalDate> datesToCheck = DateTimeMethods.listOfFutureDates(7);

        // a list to hold any matching dates
        ObservableList<Appointment> weekAppointments = FXCollections.observableArrayList();

        // loops through, and checks date of each appointment against the current date.
        for (Appointment a : allAppointments) {
            LocalDateTime datetime = a.getStartDateTime();
            LocalDate appointmentDate = datetime.toLocalDate();
            if(datesToCheck.contains(appointmentDate))
                weekAppointments.add(a);
        }
        appointmentTableView.setItems(weekAppointments);
    }

    /** This method is triggered when "month" radio button is selected. It shows the appointments for the next 30 days.*/
    public void monthRadioButtonSelected() {
        // grabs all appointments on record
        ObservableList<Appointment> allAppointments = RuntimeObjects.getAllAppointments();

        // gets a list of future dates
        ObservableList<LocalDate> datesToCheck = DateTimeMethods.listOfFutureDates(30);

        // a list to hold any matching dates
        ObservableList<Appointment> monthAppointments = FXCollections.observableArrayList();

        // loops through, and checks date of each appointment against the current date.
        for (Appointment a : allAppointments) {
            LocalDateTime datetime = a.getStartDateTime();
            LocalDate appointmentDate = datetime.toLocalDate();
            if(datesToCheck.contains(appointmentDate))
                monthAppointments.add(a);
        }
        appointmentTableView.setItems(monthAppointments);
    }

    /** This method exits the program via the Exit button */
    public void cancelButtonClicked(ActionEvent event) throws IOException {
        ControllerMethods.changeScene(event, "../View/CustomerDashboard.fxml");
    }


    /** Method to set initial conditions of the controller. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // sets a default radio button selection
        allRadioButton.setSelected(true);

        // to populate the customer table
        appointmentTableView.setItems(RuntimeObjects.getAllAppointments());

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        startTimeCol.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
        endTimeCol.setCellValueFactory(new PropertyValueFactory<>("endDateTime"));
        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        contactIdCol.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        userIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
    }
}
