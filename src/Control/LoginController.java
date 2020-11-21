package Control;

import Model.Appointment;
import Model.RuntimeObjects;
import Model.User;
import Utils.ControllerMethods;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label countryLabel;
    @FXML private Label timezoneLabel;



    /** This method that checks the validity of the login attempt */
    public void loginButtonClicked(ActionEvent event) throws IOException {

        String usernameAttempted = usernameField.getText();
        String passwordAttempted = passwordField.getText();

        // checks to see if username and password were both provided.
        if(usernameAttempted.equals("") || passwordAttempted.equals(""))
        {
            ControllerMethods.errorDialogueBox("You must provide a username and password!");
            return;
        }
        // loop through the users to check if user name in database

        User detectedUser = null;

        ObservableList<User> userObjects = RuntimeObjects.getAllUsers();
        for (User u : userObjects) {
            String userNameInDB = u.getUsername();
            if(userNameInDB.equals(usernameAttempted))
            {
                detectedUser = u;
                break;
            }
        }

        // if not found,
        if(detectedUser == null){
            ControllerMethods.errorDialogueBox("User name not found!");
            return;
        }

        // if found, compare password
        String correctPassword = detectedUser.getPassword();
        if(passwordAttempted.equals(correctPassword))
        {
            RuntimeObjects.setCurrentUser(detectedUser);
            ControllerMethods.changeScene(event, "../View/CustomerDashboard.fxml");
        }
        else
        {
            ControllerMethods.errorDialogueBox("Password incorrect!");
        }
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
