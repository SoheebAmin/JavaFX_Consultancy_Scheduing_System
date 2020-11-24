package Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
