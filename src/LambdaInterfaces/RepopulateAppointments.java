package LambdaInterfaces;

import java.sql.Connection;

/** A public interface in place to allow for a simple setup to refresh the appointments from the database, used in the reports controller and customer dashboard. */
public interface RepopulateAppointments {

    void repopulateDB(Connection conn);
}
