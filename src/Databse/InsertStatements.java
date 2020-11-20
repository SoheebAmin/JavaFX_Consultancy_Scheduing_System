package Databse;

import Utils.DBQuery;

import java.sql.*;
import java.time.LocalDateTime;

public class InsertStatements {

    /** SQL code to add customer to the database*/
    public static void insertCustomer(Connection conn, int Customer_ID, String Customer_Name, String Address,
                                      String Postal_Code, String Phone, String Created_By, int Division_ID
    ) {
        try {
            // Prepared Insert Statement for countries table
            String insertStatement = "INSERT INTO customers(Customer_ID, Customer_Name, Address, Postal_Code, Phone, " +
                    "Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID) VALUES(?,?,?,?,?,Now(),?,Now(),?,?);";

            // Create the prepared Statement Object
            DBQuery.setPreparedStatement(conn, insertStatement);

            PreparedStatement preparedStatement = DBQuery.getPreparedStatement();

            // Key-value mapping of ? to variables
            preparedStatement.setInt(1, Customer_ID);
            preparedStatement.setString(2, Customer_Name);
            preparedStatement.setString(3, Address);
            preparedStatement.setString(4, Postal_Code);
            preparedStatement.setString(5, Phone);
            preparedStatement.setString(6, Created_By);
            preparedStatement.setString(7, Created_By);
            preparedStatement.setInt(8, Division_ID);

            // Try to execute SQL statement, and gets the error if there is data incorrectly entered.
            try {
                preparedStatement.execute();

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
        }
        catch(SQLException e) {
            e.getMessage();
        }
    }

    /** SQL code to add customer to the database*/
    public static void modifyCustomer(Connection conn, int Customer_ID, String Customer_Name, String Address, String Postal_Code,
                                      String Phone, LocalDateTime Create_Date, String Created_By, String Last_Updated_By, int Division_ID
    ) {
        try {
            // Prepared Insert Statement for countries table
            String insertStatement = "INSERT INTO customers(Customer_ID, Customer_Name, Address, Postal_Code, Phone, " +
                    "Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID) VALUES(?,?,?,?,?,?,?,Now(),?,?);";

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
                    System.out.println(rows + " row(s) affected");
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


    /** SQL code to add an appointment to the database*/
    public static void insertAppointment(Connection conn, int Customer_ID, String Customer_Name, String Address,
                                      String Postal_Code, String Phone, String Created_By, int Division_ID
    ) {
        try {
            // Prepared Insert Statement for countries table
            String insertStatement = "INSERT INTO appointments(Appointment_ID, Customer_Name, Address, Postal_Code, Phone, " +
                    "Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID) VALUES(?,?,?,?,?,Now(),?,Now(),?,?);";

            // Create the prepared Statement Object
            DBQuery.setPreparedStatement(conn, insertStatement);

            PreparedStatement preparedStatement = DBQuery.getPreparedStatement();

            // Key-value mapping of ? to variables
            preparedStatement.setInt(1, Customer_ID);
            preparedStatement.setString(2, Customer_Name);
            preparedStatement.setString(3, Address);
            preparedStatement.setString(4, Postal_Code);
            preparedStatement.setString(5, Phone);
            preparedStatement.setString(6, Created_By);
            preparedStatement.setString(7, Created_By);
            preparedStatement.setInt(8, Division_ID);

            // Try to execute SQL statement, and gets the error if there is data incorrectly entered.
            try {
                preparedStatement.execute();

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
        }
        catch(SQLException e) {
            e.getMessage();
        }
    }
}


