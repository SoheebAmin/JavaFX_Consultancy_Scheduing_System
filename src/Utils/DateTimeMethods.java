package Utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;

public class DateTimeMethods {

    public static ObservableList<LocalDate> listOfFutureDates(int daysToAdd){
        ObservableList<LocalDate> listOfDays = FXCollections.observableArrayList();
        LocalDate date = LocalDate.now();
        int i = 1;
        while(i < daysToAdd) {
            listOfDays.add(date);
            System.out.println("The date added was " + date);
            date = date.plusDays(1);
            i++;
        }
        return listOfDays;
    }
}
