package Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Locale;
import java.util.TimeZone;

/**This class grabs the customers and appointments in the database at program run time, and also is updated as changes are made during the life of the application. */
public class RuntimeObjects {

    // holds the customer and appointment objects for the runtime of the program
    private static ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
    private static ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();

    // holds the list of potential contacts
    private static ObservableList<String> allContacts = FXCollections.observableArrayList();

    // holds the list of appointment types utilized by the program
    private static ObservableList<String> allAppointmentTypes = FXCollections.observableArrayList(
            "Meet and Greet", "Planning Session", "In-Depth Session", "Closing Operations");

    // holds the list of days appointments will be allowed until.
    private static ObservableList<LocalDate> allAppointmentDates = FXCollections.observableArrayList();

    // holds the list of hours allowed for appointments
    private static ObservableList<LocalTime> allAppointmentHours = FXCollections.observableArrayList();

    // list of all US Time Zone IDs
    private static ObservableList<ZoneId> allUSZoneIds = FXCollections.observableArrayList(
            ZoneId.of("US/Alaska"),
            ZoneId.of("US/Aleutian"),
            ZoneId.of("US/Arizona"),
            ZoneId.of("US/Central"),
            ZoneId.of("US/East-Indiana"),
            ZoneId.of("US/Eastern"),
            ZoneId.of("US/Hawaii"),
            ZoneId.of("US/Indiana-Starke"),
            ZoneId.of("US/Michigan"),
            ZoneId.of("US/Mountain"),
            ZoneId.of("US/Pacific"),
            ZoneId.of("US/Pacific-New"),
            ZoneId.of("US/Samoa"));

    // holds the list of all user objects and a variable for the current user
    private static ObservableList<User> allUsers = FXCollections.observableArrayList();
    private static User currentUser;

    // holds the Time zone of the user
    private static TimeZone currentTimeZone;

    // holds a boolean for if we have EST hours run past midnight into the new day for local hours
    private static boolean complexHours;

    // holds the offset from EST
    private static int offset;

    // Holds the current language
    private static Locale currentLocale;


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

    public static TimeZone getCurrentTimeZone() {
        return currentTimeZone;
    }

    public static void setCurrentTimeZone(TimeZone currentTimeZone) {
        RuntimeObjects.currentTimeZone = currentTimeZone;
    }

    public static int getOffset() {
        return offset;
    }

    public static void setOffset(int offset) {
        RuntimeObjects.offset = offset;
    }

    public static Locale getCurrentLocale() {
        return currentLocale;
    }

    public static void setCurrentLocale(Locale currentLocale) {
        RuntimeObjects.currentLocale = currentLocale;
    }

    public static boolean isComplexHours() {
        return complexHours;
    }

    public static void setComplexHours(boolean complexHours) {
        RuntimeObjects.complexHours = complexHours;
    }

    public static ObservableList<ZoneId> getAllUSZoneIds() {
        return allUSZoneIds;
    }
}
