package Control;

import Model.Customer;
import Model.RuntimeObjects;
import Utils.ControllerMethods;
import Utils.DBConnection;
import Utils.DeleteStatements;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class CustomerDashboardController implements Initializable {

    // Variables for the Customer Table tableview and columns.
    @FXML private TableView<Customer> customersTableView;
    @FXML private TableColumn<Customer, Integer> cIDCol;
    @FXML private TableColumn<Customer, String>  nameCol;
    @FXML private TableColumn<Customer, String> addressCol;
    @FXML private TableColumn<Customer, Integer> phoneCol;
    @FXML private TableColumn<Customer, String> postalCol;
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

    public void deleteButtonClicked() {
        // grabs selected customer
        Customer selectedCustomer = customersTableView.getSelectionModel().getSelectedItem();

        // abort function if null
        if(selectedCustomer == null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("You need to select a customer to delete!");
            alert.showAndWait();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this customer?");

        Optional<ButtonType> result = alert.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK) {
            // prepares the SQL Delete statement
            int customerId = selectedCustomer.getId();
            String SQLStament = "DELETE FROM customers WHERE Customer_ID =" + customerId + ";";

            // deletes the record from the database itself
            DeleteStatements.delete(DBConnection.getConn(), SQLStament);

            // then deletes it from table view, and refreshes it.
            RuntimeObjects.deleteCustomer(selectedCustomer);
            customersTableView.setItems(RuntimeObjects.getAllCustomers());
        }
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
        customersTableView.setItems(RuntimeObjects.getAllCustomers());

        cIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        postalCol.setCellValueFactory(new PropertyValueFactory<>("postal"));
        divisionCol.setCellValueFactory(new PropertyValueFactory<>("division"));
        countryCol.setCellValueFactory(new PropertyValueFactory<>("country"));

    }



}
