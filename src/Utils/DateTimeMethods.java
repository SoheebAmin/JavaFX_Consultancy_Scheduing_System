package Utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.time.LocalTime;

public class DateTimeMethods {

    private static final int MINUTES_IN_DAY = 1440;

    public static ObservableList<LocalDate> listOfFutureDates(int daysToAdd){
        ObservableList<LocalDate> listOfDays = FXCollections.observableArrayList();
        LocalDate date = LocalDate.now();
        int i = 1;
        while(i < daysToAdd) {
            listOfDays.add(date);
            date = date.plusDays(1);
            i++;
        }
        return listOfDays;
    }

    /**This method gets the list of starting time for meetings as Local time objects, stored in a list, in the specified intervals (in minutes). */
    public static ObservableList<LocalTime> listOfTimes(int meetingInterval) {
        ObservableList<LocalTime> listOfTimes = FXCollections.observableArrayList();

        int intervalsInDay = MINUTES_IN_DAY/meetingInterval; // for example, by 60 minute intervals, there are 24 intervalsInDay
        int i = 0;
        LocalTime time = LocalTime.MIDNIGHT;
        while(i < intervalsInDay)
        {
            listOfTimes.add(time);
            time = time.plusMinutes(meetingInterval);
            i++;
        }
        return listOfTimes;
    }

    /*
    Some testing stuff
    LocalTime time = LocalTime.parse("23:44");
        LocalDate date = LocalDate.now();

        LocalDateTime test = LocalDateTime.of(date, time);
        System.out.println(test);

        System.out.println(time);
     */
}
