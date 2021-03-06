package Control;

import Model.RuntimeObjects;
import Model.User;
import Utils.ControllerMethods;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimeZone;
import java.io.*;

/** This is the controller class for the Login Scene.*/
public class LoginController implements Initializable {

    // fields for the various buttons and labels
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label usernameTextLabel;
    @FXML private Label passwordTextLabel;
    @FXML private Label welcomeLabel;
    @FXML private Label countryTextLabel;
    @FXML private Label timezoneTextLabel;
    @FXML private Button exitButton;
    @FXML private Button loginButton;

    @FXML private Label countryLabel;
    @FXML private Label timezoneLabel;

    private static boolean frenchDetected = false;


    /** This method that checks the validity of the login attempt. Translations from Google Translate. */
    public void loginButtonClicked(ActionEvent event) throws IOException {
        String usernameAttempted = usernameField.getText();
        String passwordAttempted = passwordField.getText();

        // checks to see if username and password were both provided.
        if(usernameAttempted.equals("") || passwordAttempted.equals("")) {
            if(LoginController.frenchDetected)
            {
                ControllerMethods.errorDialogueBox("Vous devez fournir un nom d'utilisateur et un mot de passe!");
            }
            else
            {
                ControllerMethods.errorDialogueBox("You must provide a username and password!");
            }
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
            if(LoginController.frenchDetected)
            {
                ControllerMethods.errorDialogueBox("Utilisateur non trouvé!");
            }
            else
            {
                ControllerMethods.errorDialogueBox("User not found!");
            }
            return;
        }

        // if found, compare password
        String correctPassword = detectedUser.getPassword();
        if(passwordAttempted.equals(correctPassword))
        {
            writeToLog(detectedUser.getUsername(), true);
            RuntimeObjects.setCurrentUser(detectedUser);
            ControllerMethods.changeScene(event, "../View/CustomerDashboard.fxml");
        }
        else
        {
            writeToLog(detectedUser.getUsername(), false);
            if(LoginController.frenchDetected)
            {
                ControllerMethods.errorDialogueBox("mot de passe incorrect!");
            }
            else
            {
                ControllerMethods.errorDialogueBox("Password incorrect!");
            }
        }
    }

    /**This method will be called by the login attempt if a correct user name was provided. It will write the login attempt to login_activity.txt*/
    private static void writeToLog(String usernameAttempted, boolean wasSuccessful) {

        LocalDateTime loginAttemptTime = LocalDateTime.now();
        String toWrite;
        if(wasSuccessful)
        {
            toWrite = "User " + usernameAttempted + " successfully logged in at " + loginAttemptTime;
        }
        else
        {
            toWrite = "User " + usernameAttempted + " unsuccessfully attempted to logged in at " + loginAttemptTime;
        }
        // Write to file
        try {
            // This set up to get PrintWriter to append via FileWriter is from the top answer in Stackoverflow question 9961292.
            PrintWriter logFile = new PrintWriter(new FileWriter("login_activity.txt", true));

            logFile.println(toWrite);
            System.out.println("log file appended");
            logFile.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /** This method exits the program via the Exit button */
    public void exitButtonClicked(){
        System.exit(0);
    }

    /** Method to set initial conditions of the controller. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Locale to assume. Default is English. If FrenchDetected, French will be displayed instead.
        if(RuntimeObjects.getCurrentLocale().equals(Locale.FRENCH))
        {
            LoginController.frenchDetected = true;
        }

        if(LoginController.frenchDetected) {
            usernameTextLabel.setText("Nom d'utilisateur");
            passwordTextLabel.setText("mot de passe");
            welcomeLabel.setText("Bienvenue dans l'application. Veuillez vous connecter ci-dessous.");
            countryTextLabel.setText("pays:");
            timezoneTextLabel.setText("fuseau horaire:");
            exitButton.setText("sortie");
            loginButton.setText("s'identifier");
        }

        TimeZone currentTimeZone = RuntimeObjects.getCurrentTimeZone();

        countryLabel.setText(currentTimeZone.getID());
        timezoneLabel.setText(currentTimeZone.getDisplayName());
    }
}
