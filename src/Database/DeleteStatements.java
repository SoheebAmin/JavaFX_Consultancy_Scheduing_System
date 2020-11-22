package Database;

import Utils.DBQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

            // Confirm number of rows affected
            int rows = preparedStatement.getUpdateCount();
            if (rows > 0)
                System.out.println(rows + " row(s) deleted");
            else
                System.out.println("Nothing changed");
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

}
