import Model.RuntimeObjects;
import Model.User;
import Utils.DBConnection;
import Databse.SelectStatements;
import Utils.DateTimeMethods;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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

        // Test User. Do not comment out until log in screen built.
        User user = new User(1, "test", "test");
        RuntimeObjects.setCurrentUser(user);

        // Add all the customers and appointments in the database to the customers and appointments tableview
        SelectStatements.populateCustomersTable(conn);
        SelectStatements.populateAppointmentsTable(conn);

        // populate the contact names from the database into a an observable list
        SelectStatements.populateContacts(conn);

        // Appointment types are defined here (they do not exist in the database)
        ObservableList<String> appointmentTypes = FXCollections.observableArrayList();
        appointmentTypes.add("Meet and Greet");
        appointmentTypes.add("Planning Session");
        appointmentTypes.add("In-Depth Session");
        appointmentTypes.add("Closing Operations");
        RuntimeObjects.setAllAppointmentTypes(appointmentTypes);

        // populates list of days to be selectable for appointments from today onward, up to the specified number of days.
        ObservableList<LocalDate> generatedAppointmentDateList = DateTimeMethods.listOfFutureDates(60);
        RuntimeObjects.setAllAppointmentDates(generatedAppointmentDateList);

        // populates the hours to be selectable for appointments, given the specified intervals in minutes
        ObservableList<LocalTime> generatedAppointmentHoursList = DateTimeMethods.listOfTimes(60);
        RuntimeObjects.setAllAppointmentHours(generatedAppointmentHoursList);

        // launches JavaFX App
        launch(args);


        // Closes DB connection
        DBConnection.closeConnection();
    }
}

