package main.java.sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
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

public class ScheduleTableController extends Application {

    private String username;

    private final EventManager eventManager = new EventGateway().read();

    private final EventTableView eventTableView = new EventTableView(eventManager, username);

    @FXML
    private ToggleGroup scheduleType;

    @FXML
    private RadioButton fullSchedule;

    @FXML
    private RadioButton yourSchedule;

    @FXML
    private TextField searchText;

    @FXML
    private ChoiceBox<String> searchBy;

    @FXML
    private Button downloadButton;

    @FXML
    private Label downloadMessage;

    @FXML
    private TableColumn<Object, String> idColumn;

    @FXML
    private TableColumn <Object, String> titleColumn;

    @FXML
    private TableColumn<Object, String> typeColumn;

    @FXML
    private TableColumn<Object, String> locationColumn;

    @FXML
    private TableColumn <Object, String> datetimeColumn;

    @FXML
    private TableColumn<Object, String> speakerColumn;

    @FXML
    private TableColumn <Object, String> durationColumn;

    @FXML
    private TableColumn <Object, String> eventCapacityColumn;

    @FXML
    public TableView eventTable = new TableView<>();

    @FXML
    public void initialize() {
        setRatioButtonDefault();
        setColumnValue();
        setSearchBy();
        eventTable.setItems(eventTableView.getFilteredListEvents());
        setAllScheduleBySearchText();
        resetScheduleTable();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("ScheduleTable.fxml"));
        Parent root = loader.load();
        Scene openingScene = new Scene(root, 940, 500);
        primaryStage.setTitle("Schedule");
        primaryStage.setScene(openingScene);
        primaryStage.show();
    }

    public void setRatioButtonDefault() {
        fullSchedule.setToggleGroup(scheduleType);
        fullSchedule.setSelected(true);
        yourSchedule.setToggleGroup(scheduleType);
    }

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
                            downloadMessage.setText("Full schedule has been successfully downloaded to \n"
                                    + finalDestination + ".");
                        }
                    });
                } catch (Exception ex) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            downloadMessage.setText("Failed to download schedule due to server error.\n" +
                                    "Please try again later.");
                        }
                    });
                }
            } else {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        downloadMessage.setText("No file location was selected."); }});
                downloadMessage.setTextAlignment(TextAlignment.RIGHT);
            }
        })
    ;}

    public void constructScheduleTxt () throws IOException {
        BuildingManager buildingManager = new BuildingGateway().read();
        String scheduleString = buildingManager.getToString(eventManager);
        FileWriter scheduleWriter = new FileWriter("phase2/src/main/java/DB/Schedule.txt");
        scheduleWriter.write(scheduleString);
        scheduleWriter.close();
    }

    public void setAllScheduleBySearchText() {
        searchText.setOnKeyReleased(keyEvent -> {
            switch (searchBy.getValue())
            {
                case "ID":
                    eventTableView.getFilteredListEvents().setPredicate(p -> p.getUuid().toString().toLowerCase()
                            .contains(searchText.getText().toLowerCase().trim()));
                    break;
                case "Type":
                    eventTableView.getFilteredListEvents().setPredicate(p -> p.getType().toLowerCase().contains(
                            searchText.getText().toLowerCase().trim()));
                    break;
                case "Title":
                    eventTableView.getFilteredListEvents().setPredicate(p -> p.getTitle().toLowerCase().contains(
                            searchText.getText().toLowerCase().trim()));
                    break;
                case "Location":
                    eventTableView.getFilteredListEvents().setPredicate(p -> p.getLocation().toLowerCase().contains(
                            searchText.getText().toLowerCase().trim()));
                    break;
                case "Date Time":
                    eventTableView.getFilteredListEvents().setPredicate(p -> p.getDatetime().toString().contains(
                            searchText.getText().toLowerCase().trim()));
                    break;
                case "Speaker":
                    eventTableView.getFilteredListEvents().setPredicate(p -> p.containSpeaker(searchText.getText().
                            toLowerCase().trim()));
                    break;
            }
        });
    }


    public void setRegisteredEventBySearchText() {
        searchText.setOnKeyReleased(keyEvent -> {
            switch (searchBy.getValue())
            {
                case "ID":
                    eventTableView.getFilteredListEventsRegistered().setPredicate(p -> p.getUuid().toString().contains(
                            searchText.getText().toLowerCase().trim()));
                    break;
                case "Type":
                    eventTableView.getFilteredListEventsRegistered().setPredicate(p -> p.getType().toLowerCase().contains(
                            searchText.getText().toLowerCase().trim()));
                    break;
                case "Title":
                    eventTableView.getFilteredListEventsRegistered().setPredicate(p -> p.getTitle().toLowerCase().contains(
                            searchText.getText().toLowerCase().trim()));
                    break;
                case "Location":
                    eventTableView.getFilteredListEventsRegistered().setPredicate(p -> p.getLocation().toLowerCase().contains(
                            searchText.getText().toLowerCase().trim()));
                    break;
                case "Date Time":
                    eventTableView.getFilteredListEventsRegistered().setPredicate(p -> p.getDatetime().toString().contains(
                            searchText.getText().toLowerCase().trim()));
                    break;
                case "Speaker":
                    eventTableView.getFilteredListEventsRegistered().setPredicate(p -> p.containSpeaker(searchText.getText().toLowerCase().trim()));
                    break;
            }
        });
    }

    public void resetScheduleTable() {
        searchBy.getSelectionModel().selectedItemProperty().addListener((observableValue, old, newValue) ->
        {
            if (newValue != null)
            { searchText.setText("");
                eventTableView.getFilteredListEvents().setPredicate(null);
            }
        });
    }

    public void resetYourScheduleTable() {
        searchBy.getSelectionModel().selectedItemProperty().addListener((observableValue, old, newValue) ->
        {
            if (newValue != null)
            { searchText.setText("");
                eventTableView.getFilteredListEventsRegistered().setPredicate(null);
            }
        });
    }

    public void setSearchBy() {
        searchBy.getItems().addAll("ID", "Title", "Location", "Date Time", "Type", "Speaker");
    }

    public void setUsername(String username) {
        this.username = username;
    }

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


    public void handleYourSchedule(ActionEvent actionEvent) {
        eventTable.setItems(eventTableView.getFilteredListEventsRegistered());
        setRegisteredEventBySearchText();
        resetYourScheduleTable();
    }

    public void handleFullSchedule(ActionEvent actionEvent) {
        eventTable.setItems(eventTableView.getFilteredListEvents());
        setAllScheduleBySearchText();
        resetScheduleTable();
    }
}