package Control;

import Model.Appointment;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


import java.time.LocalDateTime;

public class ReportsController {

    // Variables for the Customer Table tableview and columns.
    @FXML private TableView<Appointment> appointmentTableView1;
    @FXML private TableColumn<Appointment, Integer> idCol1;
    @FXML private TableColumn<Appointment, String>  titleCol1;
    @FXML private TableColumn<Appointment, String> locationCol1;
    @FXML private TableColumn<Appointment, String> descriptionCol1;
    @FXML private TableColumn<Appointment, String> typeCol1;
    @FXML private TableColumn<Appointment, LocalDateTime> startTimeCol1;
    @FXML private TableColumn<Appointment, LocalDateTime> endTimeCol1;
    @FXML private TableColumn<Appointment, Integer> customerIdCol1;
    @FXML private TableColumn<Appointment, Integer> contactIdCol1;
    @FXML private TableColumn<Appointment, Integer> userIdCol1;

    // Variables for the Customer Table tableview and columns.
    @FXML private TableView<Appointment> appointmentTableView2;
    @FXML private TableColumn<Appointment, Integer> idCol2;
    @FXML private TableColumn<Appointment, String>  titleCol2;
    @FXML private TableColumn<Appointment, String> locationCol2;
    @FXML private TableColumn<Appointment, String> descriptionCol2;
    @FXML private TableColumn<Appointment, String> typeCol2;
    @FXML private TableColumn<Appointment, LocalDateTime> startTimeCol2;
    @FXML private TableColumn<Appointment, LocalDateTime> endTimeCol2;
    @FXML private TableColumn<Appointment, Integer> customerIdCol2;
    @FXML private TableColumn<Appointment, Integer> contactIdCol2;
    @FXML private TableColumn<Appointment, Integer> userIdCol2;

    // Variables for the Customer Table tableview and columns.
    @FXML private TableView<Appointment> appointmentTableView3;
    @FXML private TableColumn<Appointment, Integer> idCol3;
    @FXML private TableColumn<Appointment, String>  titleCol3;
    @FXML private TableColumn<Appointment, String> locationCol3;
    @FXML private TableColumn<Appointment, String> descriptionCol3;
    @FXML private TableColumn<Appointment, String> typeCol3;
    @FXML private TableColumn<Appointment, LocalDateTime> startTimeCol3;
    @FXML private TableColumn<Appointment, LocalDateTime> endTimeCol3;
    @FXML private TableColumn<Appointment, Integer> customerIdCol3;
    @FXML private TableColumn<Appointment, Integer> contactIdCol3;
    @FXML private TableColumn<Appointment, Integer> userIdCol3;

}
