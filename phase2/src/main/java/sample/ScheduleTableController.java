package main.java.sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.java.Entities.Event;
import main.java.Gateways.EventGateway;
import main.java.UseCases.BuildingManager;
import main.java.UseCases.EventTableView;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;

public class ScheduleTableController extends Application {

    BuildingManager buildingManager = new EventGateway().read();

    EventTableView eventTableView = new EventTableView(buildingManager);

    @FXML
    TextField searchText;

    @FXML
    ChoiceBox<String> searchBy;

    @FXML
    Button downloadButton;

    @FXML
    Label downloadMessage;

    @FXML
    TableColumn <Object, String> titleColumn;

    @FXML
    TableColumn<Object, String> locationColumn;

    @FXML
    TableColumn <Object, String> datetimeColumn;

    @FXML
    TableColumn<Object, String> speakerColumn;

    @FXML
    TableColumn <Object, String> durationColumn;

    @FXML
    TableColumn <Object, String> eventCapacityColumn;

    @FXML
    public TableView eventTable = new TableView<>();


    @FXML
    private void initialize() {
        eventTableView.setTitleColumn(titleColumn);
        eventTableView.setLocationColumn(locationColumn);
        eventTableView.setDatetimeColumn(datetimeColumn);
        eventTableView.setSpeakerColumn(speakerColumn);
        eventTableView.setDurationColumn(durationColumn);
        eventTableView.setCapacityColumn(eventCapacityColumn);

        eventTable.setItems(eventTableView.getFilteredListEvents());

        searchBy.getItems().addAll("Title", "Location", "Date Time", "Speaker");

        setScheduleBySearchText();

        resetScheduleTable();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("ScheduleTable.fxml"));
        Parent root = loader.load();
        Scene openingScene = new Scene(root, 700, 500);
        primaryStage.setTitle("Schedule");
        primaryStage.setScene(openingScene);
        primaryStage.show();
    }

    public void handleDownloadButton(ActionEvent actionEvent) throws IOException {
        downloadMessage.setText("Downloading...Please wait a few seconds");
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
                            downloadMessage.setText("Full schedule has been successfully downloaded to "
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
            }
        })
    ;}

    public void constructScheduleTxt () throws IOException {
        String scheduleString = buildingManager.toString();
        FileWriter scheduleWriter = new FileWriter("phase2/src/main/java/DB/Schedule.txt");
        scheduleWriter.write(scheduleString);
        scheduleWriter.close();
    }

    public void setScheduleBySearchText() {
        searchText.setOnKeyReleased(keyEvent ->
        {
            switch (searchBy.getValue())
            {
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
                    eventTableView.getFilteredListEvents().setPredicate(p -> p.containSpeaker(searchText.getText().toLowerCase().trim()));
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


    // test
    public static void main(String[] args) {
        launch(args);
    }



}