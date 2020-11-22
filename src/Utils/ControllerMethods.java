package Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

/** This class holds all common methods that are called by the different controllers. */
public class ControllerMethods {

    /** This method wraps together the common code to generate an error dialogue box. */
    public static void errorDialogueBox(String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }

    /** This method wraps the common code to change scenes. */
    public static void changeScene(ActionEvent event, String sceneName) throws IOException {
        Parent MainScreenParent = FXMLLoader.load(ControllerMethods.class.getResource((sceneName)));
        Scene MainScreenScene = new Scene(MainScreenParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(MainScreenScene);
        window.show();
    }

    public static void infoDialogueBox(String infoMessage) {
        Alert alert = new Alert((Alert.AlertType.INFORMATION));
        alert.setTitle("Alert");
        alert.setContentText(infoMessage);
        alert.showAndWait();
    }


}
