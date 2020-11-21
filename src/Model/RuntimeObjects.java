package Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.time.LocalTime;

/**This class grabs the customers and appointments in the database at program run time, and also is updated as changes are made during the life of the application. */
public class RuntimeObjects {



    // holds the customer and appointment objects for the runtime of the program
    private static ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
    private static ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();

    // holds the list of potential contacts
    private static ObservableList<String> allContacts = FXCollections.observableArrayList();

    // holds the list of appointment types utilized by the program
    private static ObservableList<String> allAppointmentTypes = FXCollections.observableArrayList();

    // holds the list of days appointments will be allowed until.
    private static ObservableList<LocalDate> allAppointmentDates = FXCollections.observableArrayList();

    // holds the list of hours allowed for appointments
    private static ObservableList<LocalTime> allAppointmentHours = FXCollections.observableArrayList();

    // holds the list of all user objects and a variable for the current user
    private static ObservableList<User> allUsers = FXCollections.observableArrayList();
    private static User currentUser;

    // add, delete, and update customers and appointments.

    public static void addCustomer(Customer customer) {
        allCustomers.add(customer);
    }

    public static void addAppointment(Appointment appointment) {
        allAppointments.add(appointment);
    }

    public static void deleteCustomer(Customer customer) {
        RuntimeObjects.getAllCustomers().remove(customer);
    }

    public static void deleteAppointment(Appointment appointment) {
        RuntimeObjects.getAllAppointments().remove(appointment);
    }

    public static void clearAllCustomers() {
        allCustomers.clear();
    }

    public static void clearAllAppointments() {
        allAppointments.clear();
    }


    // The getters for customers and appointments

    public static ObservableList<Customer> getAllCustomers() {
        return allCustomers;
    }

    public static ObservableList<Appointment> getAllAppointments() {
        return allAppointments;
    }

    // the getters and setters for the various lists and objects to be used in the life of the program.


    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        RuntimeObjects.currentUser = currentUser;
    }

    public static void addUser(User user) {
        allUsers.add(user);
    }

    public static ObservableList<User> getAllUsers() {
        return allUsers;
    }

    public static ObservableList<String> getAllContacts() {
        return allContacts;
    }

    public static void addContact(String contactName) {
        allContacts.add(contactName);
    }

    public static void setAllContacts(ObservableList<String> allContacts) {
        RuntimeObjects.allContacts = allContacts;
    }

    public static ObservableList<String> getAllAppointmentTypes() {
        return allAppointmentTypes;
    }

    public static void setAllAppointmentTypes(ObservableList<String> allAppointmentTypes) {
        RuntimeObjects.allAppointmentTypes = allAppointmentTypes;
    }

    public static ObservableList<LocalDate> getAllAppointmentDates() {
        return allAppointmentDates;
    }

    public static void setAllAppointmentDates(ObservableList<LocalDate> allAppointmentDates) {
        RuntimeObjects.allAppointmentDates = allAppointmentDates;
    }

    public static ObservableList<LocalTime> getAllAppointmentHours() {
        return allAppointmentHours;
    }

    public static void setAllAppointmentHours(ObservableList<LocalTime> allAppointmentHours) {
        RuntimeObjects.allAppointmentHours = allAppointmentHours;
    }
}
