package LambdaInterfaces;

import java.sql.Connection;

public interface RepopulateAppointments {

    void repopulateDB(Connection conn);
}
