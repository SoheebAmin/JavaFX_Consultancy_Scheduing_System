package Control;

import Model.Appointment;
import Model.RuntimeObjects;
import Utils.ControllerMethods;
import Utils.DBConnection;
import Utils.DeleteStatements;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/** The Controller to add customer objects to the customers list stored in the ObservableLists class */
public class AddAppointmentController implements Initializable {

    // Variables for the fields to be filled in.
    @FXML
    private TextField idText;
    @FXML private ComboBox customerCB;
    @FXML private TextField titleText;
    @FXML private TextField desciptionText;
    @FXML private ComboBox locationCB;
    @FXML private ComboBox typeCB;
    @FXML private ComboBox contactCB;
    @FXML private ComboBox dateCB;
    @FXML private ComboBox startCB;
    @FXML private ComboBox endCB;


    public void saveButtonClicked(ActionEvent event) throws IOException {
        ControllerMethods.changeScene(event, "../View/AppointmentDashboard.fxml");
    }


    /** This method returns to the MainScreenController without making any changes to the Inventory class. */
    public void cancelButtonClicked(ActionEvent event) throws IOException {
        ControllerMethods.changeScene(event, "../View/AppointmentDashboard.fxml");
    }

    /** Method to set initial conditions of the controller. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
