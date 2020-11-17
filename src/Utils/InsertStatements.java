package Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertStatements {

    public static boolean populateCustomersTable(Connection conn){

        // Prepared Insert Statement for countries table
        String insertStatement = "";

        try {
            // Create the prepared Statement Object
            DBQuery.setPreparedStatement(conn, insertStatement);

            PreparedStatement preparedStatement = DBQuery.getPreparedStatement();

            // Variables
            int Customer_ID;
            String Customer_Name, Address, Phone, Postal_Code, Country, Division;



            // execute command to get all data from the customers table;
            preparedStatement.execute(insertStatement);

            // return true if the SQL statement executed successfully.
            return true;
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

}
