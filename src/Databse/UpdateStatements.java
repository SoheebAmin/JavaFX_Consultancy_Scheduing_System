package Databse;


import Utils.DBQuery;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class UpdateStatements {

    /** SQL code to modify a customer to the database*/
    public static void modifyCustomer(Connection conn, int Customer_ID, String Customer_Name, String Address, String Postal_Code,
                                      String Phone, String Last_Updated_By, int Division_ID
    )  {
        try {
            // Prepared Insert Statement for countries table
            String updateStatement = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, " +
                    "Last_Update = Now(), Last_Updated_By = ?, Division_ID = ? WHERE Customer_ID = " + Customer_ID + ";";

            // Create the prepared Statement Object
            DBQuery.setPreparedStatement(conn, updateStatement);

            PreparedStatement preparedStatement = DBQuery.getPreparedStatement();

            // Key-value mapping of ? to variables
            preparedStatement.setString(1, Customer_Name);
            preparedStatement.setString(2, Address);
            preparedStatement.setString(3, Postal_Code);
            preparedStatement.setString(4, Phone);
            preparedStatement.setString(5, Last_Updated_By);
            preparedStatement.setInt(6, Division_ID);

            // Try to execute SQL statement, and gets the error if there is data incorrectly entered.
            try {
                preparedStatement.execute();

                // Confirm number of rows affected
                int rows = preparedStatement.getUpdateCount();
                if (rows > 0)
                    System.out.println(rows + " row(s) updated");
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
