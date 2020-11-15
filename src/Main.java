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

        // Variables for any data to be entered into the country table
        String Country, Created_Date, Created_By, Last_Updated_By;



        String insertStatement = "INSERT INTO countries(Country, Create_Date, Created_By, Last_Updated_By) VALUES('Test_Country','2020-01-02 00:00:10', 'admin', 'admin');";

        // Try to execute SQL statement, and gets the error if there is data incorrectly entered.
        try {
            statement.execute(insertStatement);

            // Confirm number of rows affected
            int rows = statement.getUpdateCount();
            if (rows > 0)
                System.out.println(rows + " row(s) affected");
            else
                System.out.println("Nothing changed");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

        // launches JavaFX App
        launch(args);

        // Closes DB connection
        DBConnection.closeConnection();
    }
}

