import Model.RuntimeObjects;
import Model.User;
import Utils.DBConnection;
import Utils.DBQuery;
import Utils.SelectStatements;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.*;

public class Main extends Application {

    public static Connection conn;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("View/CustomerDashboard.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, 900, 400));
        primaryStage.show();
    }

    /** Starts the DB connection, launches the application, and then closes the DB when the application is closed. */
    public static void main(String[] args) throws SQLException{

        // Starts DB connection
        Connection conn = DBConnection.startConnection();

        // Test customer
        //Customer testCustomer = new Customer(12, "Ted", "Ted Street", 12345, "212-212-2122", "US","New York");

        // Test Appointment
        //Appointment testAppointment = new Appointment(15, "Test", "Test Land", "Cool", "Okay", null, null, 31, 32, 33);

        // Test User
        User user = new User(1, "test", "test");
        RuntimeObjects.setCurrentUser(user);

        // Add all the customers in the database to the customers and appointments tableview
        SelectStatements.populateCustomersTable(conn);
        SelectStatements.populateAppointmentsTable(conn);

        // Add all the appointments in the database to the appointments tableview

        // launches JavaFX App
        launch(args);

        // Closes DB connection
        DBConnection.closeConnection();
    }
}

