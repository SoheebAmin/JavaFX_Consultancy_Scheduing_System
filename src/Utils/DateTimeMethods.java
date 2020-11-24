package Utils;

import Database.SelectStatements;
import Model.Appointment;
import Model.RuntimeObjects;
import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.*;

/** This class holds all the methods related to date and time that are called by controller. */
public class DateTimeMethods {

    private static final int MINUTES_OF_OFFICE_HOURS_IN_DAY = 840;

    /** This method gets the list of dates from today until the input number of days ahead, and returns as a list of LocalDates. */
    public static ObservableList<LocalDate> listOfFutureDates(int days){
        ObservableList<LocalDate> listOfDays = FXCollections.observableArrayList();
        LocalDate date = LocalDate.now();
        int i = 1;
        while(i < days) {
            listOfDays.add(date);
            date = date.plusDays(1);
            i++;
        }
        return listOfDays;
    }

    /**This method gets the list of starting time for meetings as Local time objects, stored in a list, in the specified intervals (in minutes). */
    public static ObservableList<LocalTime> listOfTimes(int meetingInterval, int offset) {
        ObservableList<LocalTime> listOfTimes = FXCollections.observableArrayList();

        int intervalsDuringOfficeHours = MINUTES_OF_OFFICE_HOURS_IN_DAY/meetingInterval;
        int i = 0;

        // 8 is added since we will use midnight plus 8 for 8am EST, plus offset of local time zone.
        int startHourInLocalTime = offset + 8;

        LocalTime time = LocalTime.MIDNIGHT.plusHours(startHourInLocalTime);

        while(i < intervalsDuringOfficeHours)
        {
            listOfTimes.add(time);
            time = time.plusMinutes(meetingInterval);
            i++;
        }
        LocalTime start = listOfTimes.get(0);
        LocalTime end = listOfTimes.get(intervalsDuringOfficeHours-1);

        // If we run past midnight in the range, we set a static boolean true to use for later adjustments in appointment adding/modifying validation.
        if(start.isAfter(end))
        {
            RuntimeObjects.setComplexHours(true);
        }

        return listOfTimes;
    }

    //USEFUL TO CONVERT BETWEEN TIMEZONES

    //ZoneId EST = ZoneId.of("America/New_York");
    //ZonedDateTime test = ZonedDateTime.now().withZoneSameInstant(EST);

    /** This method uses the users timezone to determine to offset from EST, to determine the business hours for booking appointments.*/
    public static int offsetFromEST() {

        ZonedDateTime currentZonedDateTime = ZonedDateTime.now();

        ZoneId EST = ZoneId.of("America/New_York");
        ZonedDateTime ESTZonedDateTime = currentZonedDateTime.withZoneSameInstant(EST);

        LocalDateTime currentDateTime = currentZonedDateTime.toLocalDateTime();
        LocalDateTime ESTDateTime = ESTZonedDateTime.toLocalDateTime();

        Duration duration = Duration.between(ESTDateTime, currentDateTime);

        int offset = (int)(duration.toHours());

        return offset;
    }

    /** Checks if two LocalDateTime ranges have anything overlapping. */
    public static boolean isOverlapping(int AId, LocalDateTime start, LocalDateTime end) {

        // get all appointments
        ObservableList<Appointment> allAppointments = RuntimeObjects.getAllAppointments();

        // loop through, and for matching customer, check times against appointment times (seconds add/sub to allow consecutive appointments)
        for(Appointment a :allAppointments) {
            if(a.getCustomerId() == AId) {
                LocalDateTime startToCheck = a.getStartDateTime().plusSeconds(1);
                LocalDateTime endToCheck = a.getEndDateTime().minusSeconds(1);

                if(!start.isAfter(endToCheck) && !startToCheck.isAfter(end)) //(logic modified from top answer in Stackoverflow question 17106670)
                {
                    return true;
                }
            }
        }
        return false;
    }

    /** Same method as above, however, must allow same exact appointment time if modifying an appointment. */
    public static boolean isOverlappingForModify(int customerId, LocalDateTime start, LocalDateTime end) {

        // get all appointments
        ObservableList<Appointment> allAppointments = RuntimeObjects.getAllAppointments();

        // loop through, and for matching customer, check times against appointment times (seconds add/sub to allow consecutive appointments)
        for(Appointment a :allAppointments) {
            if(a.getCustomerId() == customerId) {
                LocalDateTime startToCheck = a.getStartDateTime().plusSeconds(1);
                LocalDateTime endToCheck = a.getEndDateTime().minusSeconds(1);

                // if start times exactly the same, we are modifying this appointment, so we do not count this as an overlap.
                if (start.equals(startToCheck.minusSeconds(1))) // put back the second we took away.
                    continue;

                if(!start.isAfter(endToCheck) && !startToCheck.isAfter(end)) //(logic modified from top answer in Stackoverflow question 17106670)
                {
                    return true;
                }
            }
        }
        return false;
    }

    /** This method grabs all appointments that has the users ID and compares against the current time to see if one is in fifteen minutes. */
    public static String upComingAppointmentInfo(User user, int minutesToCheckAhead) {

        // get Id and query DB for appointment start times with this user ID.
        int userId = user.getId();
        String selectStartTime = "SELECT Start FROM appointments WHERE User_ID =" + userId + ";";
        ObservableList<LocalDateTime> systemAppointments = SelectStatements.getLocalDateTimeList(DBConnection.getConn(), selectStartTime, "Start");

        // grab the IDs of these appointments in a list that will have the same indexing/
        String selectAppointId = "SELECT Appointment_ID FROM appointments WHERE User_ID = " +userId + ";";
        ObservableList<Integer> intList = SelectStatements.getIntList(DBConnection.getConn(), selectAppointId, "Appointment_ID");

        // loop through each start time and compare against current time to see if one has less than fifteen minute difference.
        LocalDateTime currentTime = LocalDateTime.now();
        int i = 0;

        for(LocalDateTime appointmentStart : systemAppointments)
        {
            Duration duration = Duration.between(currentTime, appointmentStart);
            long minuteDifference = duration.toMinutes();
            if(0 < minuteDifference && minuteDifference < minutesToCheckAhead)
            {
                String appointmentMessage = "You have an appointment at " + appointmentStart + " with ID: " + intList.get(i);
                return appointmentMessage;
            }
            i++;
        }
        return "";
    }
}
