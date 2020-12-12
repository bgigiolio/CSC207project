package main.java.sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.TextAlignment;
import main.java.Gateways.BuildingGateway;
import main.java.Gateways.EventGateway;
import main.java.UseCases.BuildingManager;
import main.java.UseCases.EventManager;
import main.java.UseCases.EventTableView;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * <h1>ScheduleTableController</h1>
 * Controls ScheduleTable.fxml for viewing full schedule and events signed up by specific user.
 *
 * @author Morgan Chang
 * @version Phase2
 */
public class ScheduleTableController {

    /**
     * The username of current user.
     */
    private String username;

    /**
     * The EventManager of the system.
     */
    private final EventManager eventManager = new EventGateway().read();

    /**
     * The EventTableView object that stores the event information of current user and the system.
     */
    private final EventTableView eventTableView = new EventTableView(eventManager, username);

    /**
     * The ToggleGroup object that specify the schedule type.
     */
    @FXML
    private ToggleGroup scheduleType;

    /**
     * The RatioButton object for full schedule.
     */
    @FXML
    private RadioButton fullSchedule;

    /**
     * The RatioButton object for the current user's schedule.
     */
    @FXML
    private RadioButton yourSchedule;

    /**
     * The text field for user to search for specific event(s) with inputted text.
     */
    @FXML
    private TextField searchText;

    /**
     * Event properties for user to search by for specific events.
     */
    @FXML
    private ChoiceBox<String> searchBy;

    /**
     * Button that allows the user to download the full schedule.
     */
    @FXML
    private Button downloadButton;

    /**
     * Area for pop-up messages when the user click on the downloadButton.
     */
    @FXML
    private Label downloadMessage;

    /**
     * The ID column of eventTable.
     */
    @FXML
    private TableColumn<Object, String> idColumn;

    /**
     * The Title column of eventTable.
     */
    @FXML
    private TableColumn <Object, String> titleColumn;

    /**
     * The Type column of eventTable.
     */
    @FXML
    private TableColumn<Object, String> typeColumn;

    /**
     * The Location column of eventTable.
     */
    @FXML
    private TableColumn<Object, String> locationColumn;

    /**
     * The Date Time column of eventTable.
     */
    @FXML
    private TableColumn <Object, String> datetimeColumn;

    /**
     * The Speaker column of eventTable.
     */
    @FXML
    private TableColumn<Object, String> speakerColumn;

    /**
     * The Duration column of eventTable.
     */
    @FXML
    private TableColumn <Object, String> durationColumn;

    /**
     * The Capacity column of eventTable.
     */
    @FXML
    private TableColumn <Object, String> eventCapacityColumn;

    /**
     * The event table.
     */
    @FXML
    public TableView eventTable = new TableView<>();

    /**
     * Initialize this ScheduleTableController.
     */
    @FXML
    public void initialize() {
        setRatioButtonDefault();
        setColumnValue();
        setSearchBy();
        eventTable.setItems(eventTableView.getFilteredListEvents());
        setAllScheduleBySearchText();
        resetScheduleTable();
    }

    /**
     * Set the Default value of the ToggleGroup scheduleType to Full Schedule.
     */
    public void setRatioButtonDefault() {
        fullSchedule.setToggleGroup(scheduleType);
        fullSchedule.setSelected(true);
        yourSchedule.setToggleGroup(scheduleType);
    }

    /**
     * Handle the action of downloading the text file of full schedule
     * Schedule.txt to the user's local computer.
     */
    public void handleDownloadButton(ActionEvent actionEvent) throws IOException {
        downloadMessage.setText("Downloading...Please wait a few seconds");
        downloadMessage.setTextAlignment(TextAlignment.RIGHT);
        constructScheduleTxt();
        SwingUtilities.invokeLater(() -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileFilter(new FileNameExtensionFilter("*.txt", ".txt"));
            int option = chooser.showSaveDialog(null);
            if (option == JFileChooser.APPROVE_OPTION) {
                File destination = chooser.getSelectedFile();
                if (!destination.getName().endsWith(".txt")) {
                    destination = new File(destination + ".txt");
                }
                try {
                    InputStream file = new FileInputStream("phase2/src/main/java/DB/Schedule.txt");
                    Files.copy(file, destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    File finalDestination = destination;
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            downloadMessage.setText("Full schedule has been successfully downloaded to\n"
                                    + finalDestination + ".");
                        }
                    });
                } catch (Exception ex) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            downloadMessage.setText("Failed to download schedule due to\n" +
                                    "server error." + "Please try again later.");
                        }
                    });
                }
            } else {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        downloadMessage.setText("No file location was selected."); }});
            }
        })
    ;}

    /**
     * Construct the text file Schedule.txt from Building.ser.
     */
    public void constructScheduleTxt () throws IOException {
        BuildingManager buildingManager = new BuildingGateway().read();
        String scheduleString = buildingManager.getToString(eventManager);
        FileWriter scheduleWriter = new FileWriter("phase2/src/main/java/DB/Schedule.txt");
        scheduleWriter.write(scheduleString);
        scheduleWriter.close();
    }

    /**
     * Filter eventTable of scheduleType fullSchedule by user's selected searchBy value and inputted searchText.
     */
    public void setAllScheduleBySearchText() {
        searchText.setOnKeyReleased(keyEvent -> {
            String text = searchText.getText().toLowerCase().trim();
            switch (searchBy.getValue())
            {
                case "ID":
                    eventTableView.getFilteredListEvents().setPredicate(p -> p.getUuid().toString()
                            .toLowerCase().contains(text));
                    break;
                case "Type":
                    eventTableView.getFilteredListEvents().setPredicate(p -> p.getType().toLowerCase()
                            .contains(text));
                    break;
                case "Title":
                    eventTableView.getFilteredListEvents().setPredicate(p -> p.getTitle().toLowerCase()
                            .contains(text));
                    break;
                case "Location":
                    eventTableView.getFilteredListEvents().setPredicate(p -> p.getLocation().toLowerCase()
                            .contains(text));
                    break;
                case "Date Time":
                    eventTableView.getFilteredListEvents().setPredicate(p -> p.getDatetime().toString()
                            .contains(text));
                    break;
                case "Speaker":
                    eventTableView.getFilteredListEvents().setPredicate(p -> p.containSpeaker(text));
                    break;
            }
        });
    }

    /**
     * Filter eventTable of scheduleType yourSchedule by user's selected searchBy value and inputted searchText.
     */
    public void setRegisteredEventBySearchText() {
        searchText.setOnKeyReleased(keyEvent -> {
            String text = searchText.getText().toLowerCase().trim();
            switch (searchBy.getValue())
            {
                case "ID":
                    eventTableView.getFilteredListEventsRegistered().setPredicate(p -> p.getUuid().toString()
                            .contains(text));
                    break;
                case "Type":
                    eventTableView.getFilteredListEventsRegistered().setPredicate(p -> p.getType().toLowerCase()
                            .contains(text));
                    break;
                case "Title":
                    eventTableView.getFilteredListEventsRegistered().setPredicate(p -> p.getTitle().toLowerCase()
                            .contains(text));
                    break;
                case "Location":
                    eventTableView.getFilteredListEventsRegistered().setPredicate(p -> p.getLocation().toLowerCase()
                            .contains(text));
                    break;
                case "Date Time":
                    eventTableView.getFilteredListEventsRegistered().setPredicate(p -> p.getDatetime()
                            .toString().contains(text));
                    break;
                case "Speaker":
                    eventTableView.getFilteredListEventsRegistered().setPredicate(p -> p.containSpeaker(
                            text));
                    break;
            }
        });
    }

    /**
     * Reset eventTable of scheduleType fullSchedule without filtering by user's input.
     */
    public void resetScheduleTable() {
        searchBy.getSelectionModel().selectedItemProperty().addListener((observableValue, old, newValue) ->
        {
            if (newValue != null)
            { searchText.setText("");
                eventTableView.getFilteredListEvents().setPredicate(null);
            }
        });
    }

    /**
     * Filter eventTable of scheduleType yourSchedule witohout filtering by user's input.
     */
    public void resetYourScheduleTable() {
        searchBy.getSelectionModel().selectedItemProperty().addListener((observableValue, old, newValue) ->
        {
            if (newValue != null)
            { searchText.setText("");
                eventTableView.getFilteredListEventsRegistered().setPredicate(null);
            }
        });
    }

    /**
     * Set ChoiceBox object SearchBy with values ID, Title, Location, Date Time, Type, and Speaker.
     */
    public void setSearchBy() {
        searchBy.getItems().addAll("ID", "Title", "Location", "Date Time", "Type", "Speaker");
    }

    /**
     * Set username of this ScheduleTableController.
     * @param username of the current user
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Set the column name of eventTable.
     */
    public void setColumnValue() {
        eventTableView.setIdColumn(idColumn);
        eventTableView.setTypeColumn(typeColumn);
        eventTableView.setTitleColumn(titleColumn);
        eventTableView.setLocationColumn(locationColumn);
        eventTableView.setDatetimeColumn(datetimeColumn);
        eventTableView.setSpeakerColumn(speakerColumn);
        eventTableView.setDurationColumn(durationColumn);
        eventTableView.setCapacityColumn(eventCapacityColumn);
    }

    /**
     * Handle yourSchedule.
     * Set eventTable to the current user's schedule with all events this user has signed up for
     * when this user click on the RatioButton yourSchedule.
     */
    public void handleYourSchedule(ActionEvent actionEvent) {
        eventTable.setItems(eventTableView.getFilteredListEventsRegistered());
        setRegisteredEventBySearchText();
        resetYourScheduleTable();
    }

    /**
     * Handle fullSchedule.
     * Set eventTable to the full schedule of the system
     * when this user click on the RatioButton yourSchedule.
     */
    public void handleFullSchedule(ActionEvent actionEvent) {
        eventTable.setItems(eventTableView.getFilteredListEvents());
        setAllScheduleBySearchText();
        resetScheduleTable();
    }
}