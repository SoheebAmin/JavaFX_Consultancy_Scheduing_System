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
         String selectStatement = "SELECT Customer_ID, Customer_Name, Address, Postal_Code, Phone, Division, Country FROM customers AS Cs, first_level_divisions AS F, countries Ct\n" +
                                  "WHERE Cs.Division_ID = F.Division_ID AND F.COUNTRY_ID = Ct.Country_ID;";

         try {
             // Create the prepared Statement Object
             DBQuery.setPreparedStatement(conn, selectStatement);

             PreparedStatement preparedStatement = DBQuery.getPreparedStatement();

             // Variables to be populated by the pulled data.
             int Customer_ID;
             String Customer_Name, Address, Phone, Postal_Code, Country, Division;



             // execute command to get all data from the customers table;
             preparedStatement.execute(selectStatement);


             ResultSet resultSet = preparedStatement.getResultSet();


             while(resultSet.next()) // a boolean function that remains true until we scroll through each record
             {
                 Customer_ID = resultSet.getInt("Customer_ID");
                 Customer_Name = resultSet.getString("Customer_Name");
                 Address = resultSet.getString("Address");
                 Postal_Code = resultSet.getString("Postal_Code");
                 Phone = resultSet.getString("Phone");
                 Division = resultSet.getString("Division");
                 Country = resultSet.getString("Country");

                 Customer customer = new Customer(Customer_ID, Customer_Name, Address, Postal_Code, Phone, Country, Division);
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













