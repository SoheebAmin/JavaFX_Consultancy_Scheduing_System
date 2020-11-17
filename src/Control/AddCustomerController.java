package Control;

import Model.Customer;
import Model.ObjectLists;
import Utils.ControllerMethods;
import Utils.DBConnection;
import Utils.SelectStatements;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

/** The Controller to add customer objects to the customers list stored in the ObservableLists class */
public class AddCustomerController implements Initializable {

    // Variables for the fields to be filled in.
    @FXML private TextField idText;
    @FXML private TextField nameText;
    @FXML private TextField addressText;
    @FXML private TextField postalText;
    @FXML private TextField phoneText;

    private static ObservableList<String> countryCBItems = FXCollections.observableArrayList();
    @FXML private ComboBox countryCB;

    private static ObservableList<String> divisionCBItems = FXCollections.observableArrayList();
    @FXML private ComboBox divisionCB;


    public boolean saveButtonClicked(ActionEvent event) throws IOException {

        try {
            String country = countryCB.getSelectionModel().getSelectedItem().toString();
            System.out.println(country);
        }
        catch (NullPointerException e) {
            ControllerMethods.errorDialogueBox("You must select an option!");
            return false;
        }
        // clear the current customers observable list, and fetch them again from the database BUT NEED TO ACTUALLY ADD TO DB JUST ABOVE THIS
        ObjectLists.clearAllCustomers();
        Connection conn = DBConnection.getConn();
        SelectStatements.populateCustomersTable(conn);

        ControllerMethods.changeScene(event, "../View/CustomerDashboard.fxml");
        return true;
    }

    public void countryCBSelected() {
        // clears data from any previous use.
        countryCBItems = SelectStatements.getComboBoxList(DBConnection.getConn(), "countries", "Country");
        // get all the countries in the database

        countryCB.setItems(countryCBItems);
    }


    /** This method returns to the MainScreenController without making any changes to the Inventory class. */
    public void cancelButtonClicked(ActionEvent event) throws IOException {
        ControllerMethods.changeScene(event, "../View/CustomerDashboard.fxml");
    }

    /** Method to set initial conditions of the controller. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



        //get all the first level divisions for the selected country

    }
}
