package Control;

import Model.Customer;
import javafx.collections.FXCollections;
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
import java.util.Optional;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    // Variables for the Customer Table tableview and columns.
    @FXML private TableView<Customer> customersTableView;
    @FXML private TableColumn<Customer, Integer> cidCol;
    @FXML private TableColumn<Customer, String> cNameCol;
    @FXML private TableColumn<Customer, Integer> cPhoneCol;


    /** This method allows the user to add a customer */
    public void addButtonClicked(ActionEvent event) throws IOException {
        Methods.changeScene(event, "../View/AddCustomer.fxml");
    }

    /** This method allows the user to modify a customer */
    public void modifyButtonClicked(ActionEvent event) throws IOException {
        Methods.changeScene(event, "../View/modifyCustomer.fxml");
    }

    /** This method allows the user to modify a customer */
    public void appointmentsButtonClicked(ActionEvent event) throws IOException {
        Methods.changeScene(event, "../View/AppointmentDashboard.fxml");
    }

    /** This method exits the program via the Exit button */
    public void logoutButtonClicked(ActionEvent event) throws IOException {
        Methods.changeScene(event, "../View/Login.fxml");
    }



    /** Method to set initial conditions of the controller. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }



}
