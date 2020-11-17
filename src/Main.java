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

        // Sets connection a variable in main other classes can access.
        Main.conn = conn;

        // Prepared Insert Statement for selecting all the data needed to populate the
        String insertStatement = "INSERT INTO countries(Country, Create_Date, Created_By, Last_Updated_By) VALUES(?,?,?,?);";

        // Create the prepared Statement Object
        DBQuery.setPreparedStatement(conn, insertStatement);

        PreparedStatement preparedStatement = DBQuery.getPreparedStatement();

        // Variables for any data to be entered into the country table
        String Country, Created_Date, Created_By, Last_Updated_By;
        Country = "Prepared_country";
        Created_Date = "2020-10-10 12:34:56";
        Created_By = "Prepared_admin";
        Last_Updated_By = "Yet_again_admin";

        // Key-value mapping of ? to variables
        preparedStatement.setString(1, Country);
        preparedStatement.setString(2, Created_Date);
        preparedStatement.setString(3, Created_By);
        preparedStatement.setString(4, Last_Updated_By);

        // Try to execute SQL statement, and gets the error if there is data incorrectly entered.
        try {
            //preparedStatement.execute();

            // Confirm number of rows affected
            int rows = preparedStatement.getUpdateCount();
            if (rows > 0)
                System.out.println(rows + " row(s) affected");
            else
                System.out.println("Nothing changed");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

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

