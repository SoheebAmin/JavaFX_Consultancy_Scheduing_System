package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** This class holds creates the connection to the database and passes it whichever controller needs it for its operations. */
public abstract class DBConnection {

    // JDBC URL Parts
    private static final String protocol = "jdbc";
    private static final String vendorName = ":mysql:";
    private static final String ipaddress = "//wgudb.ucertify.com/WJ07zYa";

    // Full URL
    private static final String jdbcURL = protocol + vendorName + ipaddress;

    // Driver
    private static final String MySQL_JDBC_Driver = "com.mysql.cj.jdbc.Driver";
    private static Connection conn = null;

    // NOTE: This was the first driver used, which is based on the webinar, but Java warned that it is depreciated.
    // private static final String MySQL_JDBC_Driver = "com.mysql.jdbc.Driver";
    // Also, port is 3306, but was not needed to establish connection.

    // username and password
    private static final String username = "U07zYa";
    private static final String password = "53689179787";

    public static Connection startConnection() {
        try {
            Class.forName(MySQL_JDBC_Driver);
            conn = DriverManager.getConnection(jdbcURL, username, password);
            System.out.println("Connection Successful");
        }
        catch(ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void closeConnection() {
        try {
            conn.close();
            System.out.println("Connection Closed");
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Connection getConn() {
        return conn;
    }

}
