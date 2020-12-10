package main.java.sample;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import main.java.Gateways.EventGateway;
import main.java.UseCases.BuildingManager;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ScheduleController {

    @FXML
    public Button downloadButton;
    @FXML
    public VBox vbox;
    @FXML
    public Label scheduleLabel;
    @FXML
    private TextArea scheduleTxt;

    public ScheduleController() {
    }

    @FXML
    private void initialize() {
        scheduleTxt.setText(new EventGateway().read().toString());
    }

    @FXML
    public void downloadSchedule() throws IOException {
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
                    System.out.println("Schedule has been successfully downloaded to " + destination + ".");
                } catch (Exception ex) {
                    System.out.println("Failed to download schedule due to server error.\n" +
                            "Please try again later.");
                }
            } else {
                System.out.println("No file location was selected.");
            }
        });
    }

    public void constructScheduleTxt() throws IOException {
        BuildingManager buildingManager = new EventGateway().read();
        String scheduleString = buildingManager.toString();
        FileWriter scheduleWriter = new FileWriter("phase2/src/main/java/DB/Schedule.txt");
        scheduleWriter.write(scheduleString);
        scheduleWriter.close();
    }


}