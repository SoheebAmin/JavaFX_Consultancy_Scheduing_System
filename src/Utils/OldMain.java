/** This is just what the main class used to be when practicing SQL statements.
 This class just serves as a record and reference and can be safely deleted. */

/*

package Utils;

import Utils.DBConnection;
import Utils.DBQuery;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class OldMain {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("View/CustomerDashboard.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    // Starts the DB connection, launches the application, and then closes the DB when the application is closed.
    public static void main(String[] args) throws SQLException {

        // Starts DB connection
        Connection conn = DBConnection.startConnection();

        // Create the statement object and get it.
        DBQuery.setStatement(conn);
        Statement statement = DBQuery.getStatement();

        String insertStatement = "INSERT INTO countries(Country, Create_Date, Created_By, Last_Updated_By) VALUES('Test_Country','2020-20-02 00:00:10', 'admin', 'admin');";

        // Try to execute SQL statement, and gets the error if there is data incorrectly entered.
        try {
            statement.execute(insertStatement);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

        // Confirm number of rows affected
        int rows = statement.getUpdateCount();
        if (rows > 0)
            System.out.println(rows + " row(s) affected");
        else
            System.out.println("Nothing changed");

        // Print all countries in the countries table
        String selectStatement = "SELECT * FROM countries;";
        statement.execute(selectStatement);
        ResultSet resultSet = statement.getResultSet();

        while(resultSet.next()) // a boolean function that remains true until we scroll through each record
        {
            String country = resultSet.getString("country");
            System.out.println(country);
        }
        // Go to Vid 2, 28:03, to see all types, including conversions to local datetime.


        // launches JavaFX App
        launch(args);

        // Closes DB connection
        DBConnection.closeConnection();
    }
}

CODE TO GENERATE SQL VIA PREPARED STATEMENT


        // Prepared Insert Statement for countries table
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






*/

