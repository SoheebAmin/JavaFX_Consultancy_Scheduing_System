package Control;

import Database.InsertStatements;
import Database.SelectStatements;
import Model.RuntimeObjects;
import Utils.ControllerMethods;
import Utils.DBConnection;
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
    @FXML private TextField nameText;
    @FXML private TextField addressText;
    @FXML private TextField postalText;
    @FXML private TextField phoneText;
    @FXML private ComboBox<String> countryCB;
    @FXML private ComboBox<String> divisionCB;

    // Temporary variables to save combo box selection
    private static String selectedCountry = "";
    private static String selectedDivision = "";


    /** This method populates the country combo box. */
    public void countryCBSelected() {
        // calls methods to generate list of countries from the DB with an SQL select
        // Observable lists for the combo boxes
        ObservableList<String> countryCBItems = SelectStatements.getComboBoxStringList(DBConnection.getConn(), "SELECT Country FROM countries;", "Country");

        // sets the list in the combo box
        countryCB.setItems(countryCBItems);
    }

    /** This method populates the division combo box with the correct divisions on the selected country. */
    public void divisionCBSelected() {

        // checks to see if a country has been selected already.
        if(AddCustomerController.selectedCountry.equals(""))
        {
            ControllerMethods.errorDialogueBox("You must select country before you can select a division!");
        }

        // adds the current country to the SQL statement to select the first division combo box items
        String SQLStatement = "SELECT Division\n" +
                "FROM first_level_divisions F, countries C\n" +
                "WHERE F.COUNTRY_ID = C.Country_ID\n" +
                "AND Country = \"" + AddCustomerController.selectedCountry + "\";";

        // calls method to generate list of items for combo box pulled from the DB with an SQL select
        ObservableList<String> divisionCBItems = SelectStatements.getComboBoxStringList(DBConnection.getConn(), SQLStatement, "Division");

        // sets the list in the combo box
        divisionCB.setItems(divisionCBItems);
    }


    /** This method sets which country is selected to a field in the class, to be referenced by other methods that need it. */
    public void countryCBSet() {
        // try-catch deals with scenario in which nothing is selected.
        try {
            AddCustomerController.selectedCountry = countryCB.getSelectionModel().getSelectedItem();
        }
        catch (NullPointerException ignored)
        {
        }
    }

    /** This method sets which division is chosen. */
    public void divisionCBSet() {
        // try-catch deals with scenario in which nothing is selected.
        try {
            AddCustomerController.selectedDivision = divisionCB.getSelectionModel().getSelectedItem();
        }
        catch (NullPointerException ignored) {
        }
    }


    /** After validating the entries, this methods adds a new record into the database and refreshes it. */
    public boolean saveButtonClicked(ActionEvent event) throws IOException {

        boolean errorDetected = false; // boolean to mark if we will abort after

        // grab the auto-Id by checking the max ID in the DB and adding 1 to it.
        int id = SelectStatements.getAnInt(DBConnection.getConn(), "SELECT max(Customer_ID)+1 AS Customer_ID FROM customers;", "Customer_ID");

        // error check and then add customer name
        String name = nameText.getText();
        if (name.equals("")) {
            ControllerMethods.errorDialogueBox("Name Error: Please enter a name");
            errorDetected = true;
        }

        // error check, and then add customer address
        String address = addressText.getText();
        if (address.equals("")) {
            ControllerMethods.errorDialogueBox("Address Error: Please enter an address");
            errorDetected = true;
        }

        // error check, and then add postal code
        String postal = postalText.getText();
        if (postal.equals("")) {
            ControllerMethods.errorDialogueBox("Postal Code Error: Please enter a postal code");
            errorDetected = true;
        }

        // error check, and then add phone number using a regex ensuring digits, with optional dashes.
        String phone = phoneText.getText();
        if (!phone.matches("^[0-9-]*$")) {
            ControllerMethods.errorDialogueBox("Please enter a valid phone using digits & dashes");
            errorDetected = true;
        }

        // check if country is empty. If not, add the country
        String country = AddCustomerController.selectedCountry;
        if(country.equals(""))
        {
            ControllerMethods.errorDialogueBox("You must select country!");
            errorDetected = true;
        }


        // check if country is first level division is empty. If not, add the first level division.
        String division = AddCustomerController.selectedDivision;
        if(division.equals(""))
        {
            ControllerMethods.errorDialogueBox("You must select select a first level division!");
            errorDetected = true;
        }

        if(errorDetected)
            return false;

        //use selected division to grab division ID
        String SQLStatement = "SELECT Division_ID FROM first_level_divisions WHERE Division = \"" + division + "\"";
        int division_id = SelectStatements.getAnInt(DBConnection.getConn(), SQLStatement, "Division_ID");

        //Calls the insert statement to add the new customer to the database.
        InsertStatements.insertCustomer(DBConnection.getConn(), id, name, address, postal, phone, RuntimeObjects.getCurrentUser().getUsername(), division_id);

        // clear the current customers observable list, and fetch them again from the database.
        RuntimeObjects.clearAllCustomers();
        Connection conn = DBConnection.getConn();
        SelectStatements.populateCustomersTable(conn);

        // clears combo box temp data variables for future use
        AddCustomerController.selectedCountry = "";
        AddCustomerController.selectedDivision = "";

        ControllerMethods.changeScene(event, "../View/CustomerDashboard.fxml");
        return true;
    }

    /** This method returns to the MainScreenController without making any changes to the Inventory class. */
    public void cancelButtonClicked(ActionEvent event) throws IOException {
        // clears combo box temp data variables for future use
        AddCustomerController.selectedCountry = "";
        AddCustomerController.selectedDivision = "";

        ControllerMethods.changeScene(event, "../View/CustomerDashboard.fxml");
    }

    /** Method to set initial conditions of the controller. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
