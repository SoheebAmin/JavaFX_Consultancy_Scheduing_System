package Control;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML private Label countryLabel;
    @FXML private Label timezoneLabel;


    /** This method that checks the validity of the login attempt */
    public void loginButtonClicked(){
        // TO DO
        Methods.errorDialogueBox("Log in attempt failed.");
    }

    /** This method exits the program via the Exit button */
    public void exitButtonClicked(){
        System.exit(0);
    }

    /** Method to set initial conditions of the controller. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
