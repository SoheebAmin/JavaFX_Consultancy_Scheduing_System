package Utils;

import Model.Customer;
import Model.ProgramData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLCommand {

    public static boolean populateCustomersTable(Connection conn){

         // Prepared Insert Statement for countries table
         String selectStatement = "SELECT * FROM WJ07zYa.customers;";

         try {
             // Create the prepared Statement Object
             DBQuery.setPreparedStatement(conn, selectStatement);

             PreparedStatement preparedStatement = DBQuery.getPreparedStatement();

             // Variables to be populated by the pulled data.
             String Customer_Name, Address, Phone, Postal_Code;
             int Customer_ID, Division_ID; // remember to grab actual division
             String Country = "N/A"; // remember to get actual country by joining tables


             // execute command to get all data from the customers table;
             preparedStatement.execute(selectStatement);


             ResultSet resultSet = preparedStatement.getResultSet();


             while(resultSet.next()) // a boolean function that remains true until we scroll through each record
             {
                 System.out.println("loop");
                 Customer_ID = resultSet.getInt("Customer_ID");
                 Customer_Name = resultSet.getString("Customer_Name");
                 Address = resultSet.getString("Address");
                 Postal_Code = resultSet.getString("Postal_Code");
                 Phone = resultSet.getString("Phone");
                 Division_ID = resultSet.getInt("Division_ID");

                 Customer customer = new Customer(Customer_ID, Customer_Name, Address, Postal_Code, Phone, Country, "N/A");
                 ProgramData.addCustomer(customer);
             }
             // return true if the SQL statement executed successfully.
             return true;
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

}













