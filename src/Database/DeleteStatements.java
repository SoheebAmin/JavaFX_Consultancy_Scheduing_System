package Database;

import Utils.DBQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/** Delete statements that are called during the life of the program. */
public class DeleteStatements {

    public static void delete(Connection conn, String SQLStatement) {
        // Prepared select statement to grab the int
        String deleteStatement = SQLStatement;

        try {
            // Create the prepared Statement Object
            DBQuery.setPreparedStatement(conn, deleteStatement);

            PreparedStatement preparedStatement = DBQuery.getPreparedStatement();

            // execute delete command
            preparedStatement.execute(deleteStatement);
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
