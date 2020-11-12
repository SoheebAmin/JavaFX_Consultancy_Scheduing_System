package Control;

import Model.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AppointmentsController implements Initializable {

    // Variables for the Customer Table tableview and columns.
    @FXML private TableView<Customer> customersTableView;
    @FXML private TableColumn<Customer, Integer> cidCol;
    @FXML private TableColumn<Customer, String> cNameCol;
    @FXML private TableColumn<Customer, Integer> cPhoneCol;


    /** This method allows the user to add a customer */
    public void addButtonClicked(ActionEvent event) throws IOException {
        Methods.changeScene(event, "../View/AddAppointment.fxml");
    }

    /** This method allows the user to add a customer */
    public void modifyButtonClicked(ActionEvent event) throws IOException {
        Methods.changeScene(event, "../View/modifyAppointment.fxml");
    }


    /** This method exits the program via the Exit button */
    public void cancelButtonClicked(ActionEvent event) throws IOException {
        Methods.changeScene(event, "../View/Dashboard.fxml");
    }



    /** Method to set initial conditions of the controller. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }



}
