package Control;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/** The Controller to add customer objects to the customers list stored in the ObservableLists class */
public class AddAppointmentController implements Initializable {
    // Variables for the fields to be filled in.



    public void saveButtonClicked(ActionEvent event) throws IOException {
        Methods.changeScene(event, "../View/AppointmentDashboard.fxml");
    }


    /** This method returns to the MainScreenController without making any changes to the Inventory class. */
    public void cancelButtonClicked(ActionEvent event) throws IOException {
        Methods.changeScene(event, "../View/AppointmentDashboard.fxml");
    }



    /** Method to set initial conditions of the controller. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
