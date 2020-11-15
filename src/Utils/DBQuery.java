package Utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBQuery {

    // statement reference
    private static Statement statement;

    // create a statement object
    public static void setPreparedStatement(Connection conn, String SQLStatement) throws SQLException {
        statement = conn.prepareStatement(SQLStatement);
    }

    // Return statement object
    public static Statement getPreparedStatement() {
        return statement;
    }
}
