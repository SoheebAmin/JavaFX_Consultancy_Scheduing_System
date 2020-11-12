package Control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/** The Controller to add customer objects to the customers list stored in the ObservableLists class */
public class AddCustomerController implements Initializable {
    // Variables for the fields to be filled in.



    public void saveButtonClicked(ActionEvent event) throws IOException {
        Methods.changeScene(event, "../View/CustomerDashboard.fxml");
    }


    /** This method returns to the MainScreenController without making any changes to the Inventory class. */
    public void cancelButtonClicked(ActionEvent event) throws IOException {
        Methods.changeScene(event, "../View/CustomerDashboard.fxml");
    }



    /** Method to set initial conditions of the controller. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
