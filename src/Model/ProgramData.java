package Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

/**This class grabs the customers and appointments in the database at program run time, and also is updated as changes are made during the life of the application. */
public class ProgramData {

    private static ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
    private static ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();


    // add, delete, and update customers and appointments.

    public static void addCustomer(Customer customer) {
        allCustomers.add(customer);
    }

    public static void addAppointment(Appointment appointment) {
        allAppointments.add(appointment);
    }

    public static void deleteCustomer(Customer customer) {
        ProgramData.getAllCustomers().remove(customer);
    }


    // The getters

    public static ObservableList<Customer> getAllCustomers() {
        return allCustomers;
    }


    public static ObservableList<Appointment> getAllAppointments() {
        return allAppointments;
    }

}
