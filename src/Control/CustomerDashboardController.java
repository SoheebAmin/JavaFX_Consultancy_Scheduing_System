package Control;

import Model.Customer;
import Model.ProgramData;
import Utils.ControllerMethods;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerDashboardController implements Initializable {

    // Variables for the Customer Table tableview and columns.
    @FXML private TableView<Customer> customersTableView;
    @FXML private TableColumn<Customer, Integer> cIDCol;
    @FXML private TableColumn<Customer, String>  nameCol;
    @FXML private TableColumn<Customer, String> addressCol;
    @FXML private TableColumn<Customer, Integer> phoneCol;
    @FXML private TableColumn<Customer, Integer> postalCol;
    @FXML private TableColumn<Customer, String> divisionCol;
    @FXML private TableColumn<Customer, String> countryCol;


    /** This method allows the user to add a customer */
    public void addButtonClicked(ActionEvent event) throws IOException {
        ControllerMethods.changeScene(event, "../View/AddCustomer.fxml");
    }

    /** This method allows the user to modify a customer */
    public void modifyButtonClicked(ActionEvent event) throws IOException {
        ControllerMethods.changeScene(event, "../View/modifyCustomer.fxml");
    }

    /** This method allows the user to modify a customer */
    public void appointmentsButtonClicked(ActionEvent event) throws IOException {
        ControllerMethods.changeScene(event, "../View/AppointmentDashboard.fxml");
    }

    /** This method exits the program via the Exit button */
    public void logoutButtonClicked(ActionEvent event) throws IOException {
        ControllerMethods.changeScene(event, "../View/Login.fxml");
    }



    /** Method to set initial conditions of the controller. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // to populate the customer table
        customersTableView.setItems(ProgramData.getAllCustomers());

        cIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        postalCol.setCellValueFactory(new PropertyValueFactory<>("postal"));
        divisionCol.setCellValueFactory(new PropertyValueFactory<>("division"));
        countryCol.setCellValueFactory(new PropertyValueFactory<>("country"));

    }



}
