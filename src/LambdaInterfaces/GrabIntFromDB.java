package LambdaInterfaces;

import Utils.DBConnection;

import java.sql.Connection;

/** A public interface in place to allow for a simple setup to grab an integer from the database, used in the reports controller. */
public interface GrabIntFromDB {

    int getAnInt(Connection conn, String SQLStatement, String column);
}
