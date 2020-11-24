package LambdaInterfaces;

import Utils.DBConnection;

import java.sql.Connection;

public interface GrabIntFromDB {

    int getAnInt(Connection conn, String SQLStatement, String column);
}
