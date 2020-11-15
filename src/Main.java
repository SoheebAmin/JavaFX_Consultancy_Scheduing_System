import Utils.DBConnection;
import Utils.DBQuery;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("View/CustomerDashboard.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    /** Starts the DB connection, launches the application, and then closes the DB when the application is closed. */
    public static void main(String[] args) throws SQLException{

        // Starts DB connection
        Connection conn = DBConnection.startConnection();

        // Create the statement object and get it.
        DBQuery.setStatement(conn);
        Statement statement = DBQuery.getStatement();

        String insertStatement = "INSERT INTO countries(Country, Create_Date, Created_By, Last_Updated_By) VALUES('Test_Country','2020-02-02 00:00:10', 'admin', 'admin');";

        // Execute SQL statement
        statement.execute(insertStatement);

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
            String country = resultSet.getString("country"); // Go to Vid 2, 28:03, to see all types.
            System.out.println(country);
        }


        // launches JavaFX App
        launch(args);

        // Closes DB connection
        DBConnection.closeConnection();
    }
}
