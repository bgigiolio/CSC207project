package main.java.Controllers;

import main.java.Entities.Event;
import main.java.Gateways.EventGateway;
import main.java.UseCases.BuildingManager;
import main.java.UseCases.Schedule2;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;

import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class ScheduleSystem {
    EventGateway eventGateway = new EventGateway();

    public void updateEventDB(String room, Schedule2 schedule) throws ClassNotFoundException {
        BuildingManager buildingManager = eventGateway.read();
        buildingManager.updateScheduleOfRoom(room, schedule);
        eventGateway.save(buildingManager);
    }

    public void constructScheduleTxt() throws IOException {
        BuildingManager buildingManager = eventGateway.read();
        String scheduleString = buildingManager.toString();
        FileWriter scheduleWriter = new FileWriter("phase2/src/main/java/DB/Schedule.txt");
        scheduleWriter.write(scheduleString);
        scheduleWriter.close();
    }

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

    /*
    public static void main(String[] arg) throws ClassNotFoundException {
        Event createdEvent = new Event("event1", "room1",
                LocalDateTime.of(2020, 12, 15, 1, 0, 0),
                1, 100);
        Schedule2 schedule = new EventGateway().getEvents().getSchedule("room1");
        schedule.addEvent(createdEvent);
        BuildingManager buildingManager = new EventGateway().getEvents();
        buildingManager.updateBuildingManager("room1", schedule);
        new EventGateway().setEvents(buildingManager);


        BuildingManager bm = new EventGateway().getEvents();
        for (String room : bm.getBuilding().keySet()) {
            Schedule2 s = bm.getBuilding().get(room);
            System.out.println(room);
            for (LocalDateTime time : s.getScheduleMap().keySet()) {
                Event event = s.getScheduleMap().get(time);
                System.out.println(time + event.getTitle() + event.getLocation());
            }
        }
    }
     */
}