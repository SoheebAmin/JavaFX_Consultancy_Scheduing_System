package Utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBQuery {

    // statement reference
    private static Statement statement;

    // create a statement object
    public static void setStatement(Connection conn) throws SQLException {
        statement = conn.createStatement();
    }

    // Return statement object
    public static Statement getStatement() {
        return statement;
    }
}
