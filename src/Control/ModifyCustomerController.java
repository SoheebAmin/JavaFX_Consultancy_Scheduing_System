package Control;

import Databse.DeleteStatements;
import Databse.UpdateStatements;
import Model.Customer;
import Model.RuntimeObjects;
import Utils.ControllerMethods;
import Utils.DBConnection;
import Databse.InsertStatements;
import Databse.SelectStatements;
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
import java.time.LocalDateTime;
import java.util.ResourceBundle;

/** The Controller to add customer objects to the customers list stored in the ObservableLists class */
public class ModifyCustomerController implements Initializable {

    // Variables for the fields to be filled in automatically via setCustomerInfo method.
    @FXML private TextField idText;
    @FXML private TextField nameText;
    @FXML private TextField addressText;
    @FXML private TextField postalText;
    @FXML private TextField phoneText;
    @FXML private ComboBox<String> countryCB;
    @FXML private ComboBox<String> divisionCB;

    // Observable lists for the combo boxes
    private static ObservableList<String> countryCBItems = FXCollections.observableArrayList();
    private static ObservableList<String> divisionCBItems = FXCollections.observableArrayList();


    // Temporary variables to save combo box selection
    private static String selectedCountry = "";
    private static String selectedDivision = "";

    // ID of current customer being modified
    private static int currentCustomer;

    
    /** This method allows the Customer Dashboard to send the data of the selected customer to the created controller object. */
    public void setCustomerInfo(Customer customer) {
        // sets the current id being worked on
        ModifyCustomerController.currentCustomer = customer.getId();

        // sends all the data to the text fields.
        idText.setText((String.valueOf((customer.getId()))));
        nameText.setText(customer.getName());
        addressText.setText(customer.getAddress());
        postalText.setText(customer.getPostal());
        phoneText.setText(customer.getPhone());
        countryCB.setValue(customer.getCountry());
        divisionCB.setValue(customer.getDivision());

        // sets the customer object values in temp vars to be used by the combo boxes.
        selectedCountry = customer.getCountry();
        selectedDivision = customer.getDivision();
    }


    /** This method populates the country combo box. */
    public void countryCBSelected() {
        // calls methods to generate list of countries from the DB with an SQL select
        countryCBItems = SelectStatements.getComboBoxStringList(DBConnection.getConn(), "SELECT Country FROM countries;", "Country");

        // sets the list in the combo box
        countryCB.setItems(countryCBItems);
    }

    public void divisionCBSelected() {
        // checks to see if a country has been selected already.
        if(ModifyCustomerController.selectedCountry == "")
        {
            ControllerMethods.errorDialogueBox("You must select country before you can select a division!");
        }

        // adds the current country to the SQL statement to select the first division combo box items
        String SQLStatement = "SELECT Division\n" +
                "FROM first_level_divisions F, countries C\n" +
                "WHERE F.COUNTRY_ID = C.Country_ID\n" +
                "AND Country = \"" + ModifyCustomerController.selectedCountry + "\";";

        // calls method to generate list of items for combo box pulled from the DB with an SQL select
        divisionCBItems = SelectStatements.getComboBoxStringList(DBConnection.getConn(), SQLStatement, "Division");

        // sets the list in the combo box
        divisionCB.setItems(divisionCBItems);
    }

    /** This method sets which country is selected to a field in the class, to be referenced by other methods that need it. */
    public void countryCBSet() {
        // try-catch deals with scenario in which nothing is selected.
        try {
            ModifyCustomerController.selectedCountry = countryCB.getSelectionModel().getSelectedItem().toString();
        }
        catch (NullPointerException e)
        {
            return;
        }
    }

    /** This method sets which division is chosen. */
    public void divisionCBSet() {
        // try-catch deals with scenario in which nothing is selected.
        try {
            ModifyCustomerController.selectedDivision = divisionCB.getSelectionModel().getSelectedItem().toString();
        }
        catch (NullPointerException e) {
            return;
        }
    }

    public boolean saveButtonClicked(ActionEvent event) throws IOException {

        boolean errorDetected = false; // boolean to mark if we will abort after

        // Grabs ID from current customer static variable
        int id = ModifyCustomerController.currentCustomer;

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
        String country = ModifyCustomerController.selectedCountry;
        if(country.equals(""))
        {
            ControllerMethods.errorDialogueBox("You must select country!");
            errorDetected = true;
        }


        // check if country is first level division is empty. If not, add the first level division.
        String division = ModifyCustomerController.selectedDivision;
        if(division.equals(""))
        {
            ControllerMethods.errorDialogueBox("You must select select a first level division!");
            errorDetected = true;
        }

        if(errorDetected == true)
            return false;

        //use selected division to grab division ID
        String insertStatement = "SELECT Division_ID FROM first_level_divisions WHERE Division = \"" + division + "\"";
        int division_id = SelectStatements.getAnInt(DBConnection.getConn(), insertStatement, "Division_ID");

        //Calls the insert statement to add the new customer to the database.
        UpdateStatements.modifyCustomer(DBConnection.getConn(), id, name, address, postal, phone, Create_Date,
                                        Created_By, RuntimeObjects.getCurrentUser().getUsername(), division_id);


        // clear the current customers observable list, and fetch them again from the database.
        RuntimeObjects.clearAllCustomers();
        Connection conn = DBConnection.getConn();
        SelectStatements.populateCustomersTable(conn);

        // clears combo box temp data variables for future use
        ModifyCustomerController.selectedCountry = "";
        ModifyCustomerController.selectedDivision = "";

        ControllerMethods.changeScene(event, "../View/CustomerDashboard.fxml");
        return true;
    }


    /** This method returns to the MainScreenController without making any changes to the Inventory class. */
    public void cancelButtonClicked(ActionEvent event) throws IOException {
        // clears combo box temp data variables for future use
        ModifyCustomerController.selectedCountry = "";
        ModifyCustomerController.selectedDivision = "";

        ControllerMethods.changeScene(event, "../View/CustomerDashboard.fxml");


    }

    /** Method to set initial conditions of the controller. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
