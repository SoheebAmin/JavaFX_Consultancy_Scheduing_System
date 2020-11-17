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

    // currently selected country
    private static String selectedCountry = "";



    public void countryCBSelected() {
        // selects all countries currently in the database
        countryCBItems = SelectStatements.getComboBoxList(DBConnection.getConn(), "SELECT Country FROM countries;", "Country");
        countryCB.setItems(countryCBItems);
    }

    /** This method is to set which country has been selected, so that it can be checked by the division combo box before selecting the appropriate divisions from the database. */
    public void countryCBSet() {
        AddCustomerController.selectedCountry = countryCB.getSelectionModel().getSelectedItem().toString();
    }

    public void divisionCBSelected() {
        if(AddCustomerController.selectedCountry == "")
        {
            ControllerMethods.errorDialogueBox("You must select country before you can select a division!");
        }
        // checks to see which country is currently selected in the country combo box.
        String SQLStatement = "SELECT Division\n" +
                "FROM first_level_divisions F, countries C\n" +
                "WHERE F.COUNTRY_ID = C.Country_ID\n" +
                "AND Country = \"" + AddCustomerController.selectedCountry + "\";";
        divisionCBItems = SelectStatements.getComboBoxList(DBConnection.getConn(), SQLStatement, "Division");
        divisionCB.setItems(divisionCBItems);
    }

    public boolean saveButtonClicked(ActionEvent event) throws IOException {

        if(AddCustomerController.selectedCountry == "")
        {
            ControllerMethods.errorDialogueBox("You must select country and then a first level division!");
            return false;
        }

        // clear the current customers observable list, and fetch them again from the database BUT NEED TO ACTUALLY ADD TO DB JUST ABOVE THIS
        ObjectLists.clearAllCustomers();
        Connection conn = DBConnection.getConn();
        SelectStatements.populateCustomersTable(conn);

        AddCustomerController.selectedCountry = ""; // cleared for next use.
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
