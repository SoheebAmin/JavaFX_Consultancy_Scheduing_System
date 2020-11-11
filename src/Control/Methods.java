package Control;
import javafx.scene.control.Alert;

/** This class holds all common methods that are called by the different controllers. */
public class Methods {

    /** This method wraps together the common code to generate an error dialogue box. */
    public static void errorDialogueBox(String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }



}
