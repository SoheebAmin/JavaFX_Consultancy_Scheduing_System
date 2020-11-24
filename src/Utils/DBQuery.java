package Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/** This class holds the boiler plate code for a database query.. */
public class DBQuery {

    // statement reference
    private static PreparedStatement preparedStatement;

    // create a statement object
    public static void setPreparedStatement(Connection conn, String SQLStatement) throws SQLException {
        preparedStatement = conn.prepareStatement(SQLStatement);
    }

    // Return statement object
    public static PreparedStatement getPreparedStatement() {
        return preparedStatement;
    }
}
