package Databse;

//UPDATE Customers
//        SET ContactName = 'Alfred Schmidt', City= 'Frankfurt'
//        WHERE CustomerID = 1;

import Utils.DBQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class UpdateStatements {

    /** SQL code to modify a customer to the database*/
    public static void modifyCustomer(Connection conn, int Customer_ID, String Customer_Name, String Address, String Postal_Code,
                                      String Phone, LocalDateTime Create_Date, String Created_By, String Last_Updated_By, int Division_ID
    ) {
        try {
            // Prepared Insert Statement for countries table
            String insertStatement = "UPDATE customers SET Customer_Name = '', Address ='', Postal_Code = '', Phone = '', " +
                    "Last_Update = '', Last_Updated_By = '', Division_ID = '' WHERE Customer_ID = " + Customer_ID + ";";

            // Create the prepared Statement Object
            DBQuery.setPreparedStatement(conn, insertStatement);

            PreparedStatement preparedStatement = DBQuery.getPreparedStatement();

            // Key-value mapping of ? to variables
            preparedStatement.setInt(1, Customer_ID);
            preparedStatement.setString(2, Customer_Name);
            preparedStatement.setString(3, Address);
            preparedStatement.setString(4, Postal_Code);
            preparedStatement.setString(5, Phone);
            preparedStatement.setTimestamp(6, Timestamp.valueOf(Create_Date));
            preparedStatement.setString(7, Created_By);
            preparedStatement.setString(8, Last_Updated_By);
            preparedStatement.setInt(9, Division_ID);

            // Try to execute SQL statement, and gets the error if there is data incorrectly entered.
            try {
                preparedStatement.execute();

                // Confirm number of rows affected
                int rows = preparedStatement.getUpdateCount();
                if (rows > 0)
                    System.out.println(rows + " row(s) added");
                else
                    System.out.println("Nothing changed");
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
        catch(SQLException e) {
            e.getMessage();
        }
    }


}
