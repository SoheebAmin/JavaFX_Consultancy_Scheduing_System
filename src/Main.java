import Model.RuntimeObjects;
import Utils.DBConnection;
import Database.SelectStatements;
import Utils.DateTimeMethods;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Locale;
import java.util.TimeZone;

/** This is the main method which calls all methods to set the initial conditions of the application based on the current state of the database.*/
public class Main extends Application {

    public static Connection conn;

    @Override
    public void start(Stage primaryStage) throws Exception {


        //Parent root = FXMLLoader.load(getClass().getResource("View/CustomerDashboard.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("View/Login.fxml"));

        if (RuntimeObjects.getCurrentLocale().equals(Locale.FRENCH))
            primaryStage.setTitle("Demande de rendez-vous");
        else
            primaryStage.setTitle("Appointment Application");
        primaryStage.setScene(new Scene(root, 420, 290));
        primaryStage.show();
    }

    /** Starts the DB connection, launches the application, and then closes the DB when the application is closed. */
    public static void main(String[] args) {

        // Starts DB connection
        Connection conn = DBConnection.startConnection();

        // Enable for testing French
        //Locale.setDefault(new Locale("fr"));

        // Get the default locale and store it.
        Locale currentLocale = Locale.getDefault();
        RuntimeObjects.setCurrentLocale(currentLocale);

        // Enable to pretend you are in Los Angeles
        //TimeZone.setDefault(TimeZone.getTimeZone("America/Los_Angeles"));

        // Get the timezone and store it
        TimeZone timeZoneToStore = TimeZone.getDefault();
        RuntimeObjects.setCurrentTimeZone(timeZoneToStore);

        //Gets the difference between your local time and EST time for office hours, and stores it.
        int offset = DateTimeMethods.offsetFromEST();
        RuntimeObjects.setOffset(offset);

        // populates list of days to be selectable for appointments from today onward, up to the specified number of days
        ObservableList<LocalDate> generatedAppointmentDateList = DateTimeMethods.listOfFutureDates(90);
        RuntimeObjects.setAllAppointmentDates(generatedAppointmentDateList);

        // populates the hours to be selectable for appointments, given the specified intervals in minutes
        ObservableList<LocalTime> generatedAppointmentHoursList = DateTimeMethods.listOfTimes(30, offset);
        RuntimeObjects.setAllAppointmentHours(generatedAppointmentHoursList);

        // Add all the customers and appointments in the database to the customers and appointments tableview
        SelectStatements.populateCustomersTable(conn);
        SelectStatements.populateAppointmentsTable(conn);

        // Adds all the users from the database into an observable list
        SelectStatements.populateUsers(conn);

        // populate the contact names from the database into a an observable list
        SelectStatements.populateContacts(conn);

        // launches JavaFX App
        launch(args);

        // Closes DB connection
        DBConnection.closeConnection();
    }
}

