package Control;

import Model.Appointment;
import Model.RuntimeObjects;
import Utils.ControllerMethods;
import Utils.DBConnection;
import Databse.DeleteStatements;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;

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


    /** This method allows the user to add a customer */
    public void addButtonClicked(ActionEvent event) throws IOException {
        ControllerMethods.changeScene(event, "../View/AddAppointment.fxml");
    }

    /** This method allows the user to add a customer */
    public void modifyButtonClicked(ActionEvent event) throws IOException {
        ControllerMethods.changeScene(event, "../View/modifyAppointment.fxml");
    }

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
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this appointment?");

        Optional<ButtonType> result = alert.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK) {

            // prepares the SQL Delete statement
            int appointmentId = selectedAppointment.getId();
            String SQLStament = "DELETE FROM appointments WHERE Customer_ID =" + appointmentId + ";";

            // deletes the record from the database itself
            DeleteStatements.delete(DBConnection.getConn(), SQLStament);

            // then deletes it from table view, and refreshes it.
            RuntimeObjects.deleteAppointment(selectedAppointment);
            appointmentTableView.setItems(RuntimeObjects.getAllAppointments());
        }
    }



    /** This method exits the program via the Exit button */
    public void cancelButtonClicked(ActionEvent event) throws IOException {
        ControllerMethods.changeScene(event, "../View/CustomerDashboard.fxml");
    }



    /** Method to set initial conditions of the controller. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // to populate the customer table
        appointmentTableView.setItems(RuntimeObjects.getAllAppointments());

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        startTimeCol.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        endTimeCol.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        contactIdCol.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        userIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));

    }



}
