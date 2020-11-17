package Control;

import Model.RuntimeObjects;
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

    // currently selected country
    private static String selectedCountry = "";


    /** This method populates the country combo box. */
    public void countryCBSelected() {
        // calls methods to generate list of countries from the DB with an SQL select
        countryCBItems = SelectStatements.getComboBoxList(DBConnection.getConn(), "SELECT Country FROM countries;", "Country");

        // sets the list in the combo box
        countryCB.setItems(countryCBItems);
    }

    /** This method sets which country is selected to a field in the class, to be referenced by other methods that need it. */
    public void countryCBSet() {
        AddCustomerController.selectedCountry = countryCB.getSelectionModel().getSelectedItem().toString();
    }

    /** This method populates the division combo box with the correct divisions on the selected country. */
    public void divisionCBSelected() {

        // checks to see if a country has been selected already.
        if(AddCustomerController.selectedCountry == "")
        {
            ControllerMethods.errorDialogueBox("You must select country before you can select a division!");
        }

        // adds the current country to the SQL statement to select the first division combo box items
        String SQLStatement = "SELECT Division\n" +
                "FROM first_level_divisions F, countries C\n" +
                "WHERE F.COUNTRY_ID = C.Country_ID\n" +
                "AND Country = \"" + AddCustomerController.selectedCountry + "\";";

        // calls method to generate list of items for combo box pulled from the DB with an SQL select
        divisionCBItems = SelectStatements.getComboBoxList(DBConnection.getConn(), SQLStatement, "Division");

        // sets the list in the combo box
        divisionCB.setItems(divisionCBItems);
    }

    /** After validating the entries, this methods adds a new record into the database and refreshes it. */
    public boolean saveButtonClicked(ActionEvent event) throws IOException {

        // grab the auto-Id by checking the max ID in the DB and adding 1 to it.
        int id = SelectStatements.getId(DBConnection.getConn(), "SELECT max(Customer_ID)+1 AS Customer_ID FROM customers;", "Customer_ID");

        // error check and then add customer name


        // error check, and then add customer address

        // error check, and then add postal code

        // error check, and then add phone number

        // check if country is empty. If not, add the country
        if(AddCustomerController.selectedCountry == "")
        {
            ControllerMethods.errorDialogueBox("You must select country and then a first level division!");
            return false;
        }
        // CODE TO ADD SELECTED COUNTRY HERE


        // check if country is first level division is empty. If not, add the first level division.
        if("SOMETHING" != "SOMETHING")
        {
            ControllerMethods.errorDialogueBox("You must select select a first level division!");
            return false;
        }
        // CODE TO ADD SELECTED FIRST LEVEL DIVISION HERE

        // clear the current customers observable list, and fetch them again from the database BUT NEED TO ACTUALLY ADD TO DB JUST ABOVE THIS
        RuntimeObjects.clearAllCustomers();
        Connection conn = DBConnection.getConn();
        SelectStatements.populateCustomersTable(conn);

        AddCustomerController.selectedCountry = ""; // clear the selected country for next use.
        ControllerMethods.changeScene(event, "../View/CustomerDashboard.fxml");
        return true;
    }

    /** This method returns to the MainScreenController without making any changes to the Inventory class. */
    public void cancelButtonClicked(ActionEvent event) throws IOException {
        AddCustomerController.selectedCountry = ""; // cleared for next use.
        ControllerMethods.changeScene(event, "../View/CustomerDashboard.fxml");
    }

    /** Method to set initial conditions of the controller. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



        //get all the first level divisions for the selected country

    }
}
