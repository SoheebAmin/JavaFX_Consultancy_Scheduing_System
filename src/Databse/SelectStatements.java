package Databse;

import Model.Appointment;
import Model.Customer;
import Model.RuntimeObjects;
import Utils.DBQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class SelectStatements {

    public static boolean populateCustomersTable(Connection conn){

         // Prepared select statement for customers table
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
                 RuntimeObjects.addCustomer(customer);
             }
             // return true if the SQL statement executed successfully.
             return true;
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static boolean populateAppointmentsTable(Connection conn){

        // Prepared select statement for appointments table
        String selectStatement = "SELECT * FROM appointments;";

        try {
            // Create the prepared Statement Object
            DBQuery.setPreparedStatement(conn, selectStatement);

            PreparedStatement preparedStatement = DBQuery.getPreparedStatement();

            // Variables to be populated by the pulled data.
            int Appointment_ID, Customer_ID, User_ID, Contact_ID;
            String Title, Description, Location, Type;
            LocalDateTime Start, End;

            // execute command to get all data from the customers table;
            preparedStatement.execute(selectStatement);


            ResultSet resultSet = preparedStatement.getResultSet();


            while(resultSet.next()) // a boolean function that remains true until we scroll through each record
            {
                Appointment_ID = resultSet.getInt("Appointment_ID");
                Title = resultSet.getString("Title");
                Location = resultSet.getString("Location");
                Description = resultSet.getString("Description");
                Type = resultSet.getString("Type");
                Start = resultSet.getTimestamp("Start").toLocalDateTime();
                End = resultSet.getTimestamp("End").toLocalDateTime();
                Customer_ID = resultSet.getInt("Customer_ID");
                Contact_ID = resultSet.getInt("Contact_ID");
                User_ID = resultSet.getInt("User_ID");

                Appointment appointment= new Appointment(Appointment_ID, Title, Location, Description, Type, Start, End, Customer_ID, Contact_ID, User_ID);
                RuntimeObjects.addAppointment(appointment);
            }
            // return true if the SQL statement executed successfully.
            return true;
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static ObservableList<String> getComboBoxList(Connection conn, String SQLStatement, String column) {
        // list to populate
        ObservableList<String> CBItems = FXCollections.observableArrayList();

        // Prepared select statement for countries
        String selectStatement = SQLStatement;

        try {
            // Create the prepared Statement Object
            DBQuery.setPreparedStatement(conn, selectStatement);

            PreparedStatement preparedStatement = DBQuery.getPreparedStatement();

            // variable to populate
            String comboItem;

            // execute command to get desired data for the combo boxes
            preparedStatement.execute(selectStatement);

            ResultSet resultSet = preparedStatement.getResultSet();

            while(resultSet.next()) // a boolean function that remains true until we scroll through each record
            {
                comboItem = resultSet.getString(column);
                CBItems.add(comboItem);
            }
            // return true if the SQL statement executed successfully.
            return CBItems;
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static int getId(Connection conn, String SQLStatement, String column) {

        // Prepared select statement to grab the int
        String selectStatement = SQLStatement;

        try {
            // Create the prepared Statement Object
            DBQuery.setPreparedStatement(conn, selectStatement);

            PreparedStatement preparedStatement = DBQuery.getPreparedStatement();

            // int to return
            int id;

            // execute command to get desired data for the combo boxes
            preparedStatement.execute(selectStatement);

            ResultSet resultSet = preparedStatement.getResultSet();
            while(resultSet.next()) // a boolean function that remains true until we scroll through each record
            {
                id = resultSet.getInt(column);
                return id;
            }
            // return -1 if failed
            return -1;
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("getId failed");
            return 1;
        }
    }

}













