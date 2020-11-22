package Utils;

import Model.RuntimeObjects;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.*;
import java.util.TimeZone;

public class DateTimeMethods {

    private static final int MINUTES_OF_OFFICE_HOURS_IN_DAY = 840;

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
}
