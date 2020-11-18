package Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
