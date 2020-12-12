package main.java.Controllers;

import main.java.Gateways.BuildingGateway;
import main.java.UseCases.BuildingManager;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * <h1>ScheduleSystem</h1>
 * Controls the whole schedule in the system.
 *
 * @author Morgan Chang
 * @version phase2
 */
public class ScheduleSystem {
    BuildingGateway buildingGateway = new BuildingGateway();

    /**
     * Construct the text file Schedule.txt from the event database Event.ser.
     */
    public void constructScheduleTxt() throws IOException {
        BuildingManager buildingManager = buildingGateway.read();
        String scheduleString = buildingManager.toString();
        FileWriter scheduleWriter = new FileWriter("phase2/src/main/java/DB/Schedule.txt");
        scheduleWriter.write(scheduleString);
        scheduleWriter.close();
    }

    /**
     * Handle the action of downloading the schedule Schedule.txt to the user's local computer.
     */
    public void downloadSchedule(){
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
                    System.out.println("Type A to see menu again, or select another option.");
                } catch (Exception ex) {
                    System.out.println("Failed to download schedule due to server error.\n" +
                            "Please select another menu option. Type A to see menu again.");
                    downloadSchedule();
                }
            } else {
                System.out.println("No file location was selected.\n" +
                        "Please select another menu option. Type A to see menu again.\"");
            }
        });
    }

}