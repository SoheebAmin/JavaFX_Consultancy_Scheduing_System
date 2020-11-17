package Control;

import Model.Customer;
import Model.RuntimeObservableLists;
import Utils.ControllerMethods;
import Utils.DBConnection;
import Utils.SelectStatements;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

/** The Controller to add customer objects to the customers list stored in the ObservableLists class */
public class AddCustomerController implements Initializable {

    // Variables for the fields to be filled in.
    @FXML private TextField idText;
    //@FXML private ComboBox nameText;
    @FXML private TextField addressText;
    @FXML private TextField postalText;
    @FXML private TextField phoneText;
    //@FXML private ComboBox countryCB;
    //@FXML private ComboBox divisionCB;


    public void saveButtonClicked(ActionEvent event) throws IOException {
        Customer testCustomer = new Customer(12, "Ted", "Ted Street", "12345", "212-212-2122", "US","New York");
        RuntimeObservableLists.addCustomer(testCustomer);

        // clear the current customers observable list, and fetch them again from the database BUT NEED TO ACTUALLY ADD TO DB JUST ABOVE THIS
        RuntimeObservableLists.clearAllCustomers();
        Connection conn = DBConnection.getConn();
        SelectStatements.populateCustomersTable(conn);

        ControllerMethods.changeScene(event, "../View/CustomerDashboard.fxml");
    }


    /** This method returns to the MainScreenController without making any changes to the Inventory class. */
    public void cancelButtonClicked(ActionEvent event) throws IOException {
        ControllerMethods.changeScene(event, "../View/CustomerDashboard.fxml");
    }

    /** Method to set initial conditions of the controller. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
