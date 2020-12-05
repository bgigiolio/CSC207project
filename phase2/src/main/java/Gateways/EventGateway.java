package main.java.Gateways;

import main.java.UseCases.BuildingManager;
import java.io.*;

/**
 * <h1>EventGateway</h1>
 * This Gateway class is responsible for retrieving and storing a copy of the building in the Events.ser file.
 * This should only be called by the ImportExportController.
 * @author Zachary Werle
 * @version Phase 1
 */
public class EventGateway implements Serializable {

    /**
     * This method retrieves a BuildingManager object from Events.ser.
     * @return A BuildingManager object.
     * @throws ClassNotFoundException Handles the case where no serializable class is found.
     */
    public BuildingManager getEvents() throws ClassNotFoundException {
        BuildingManager events = new BuildingManager("Building");
        try {
            InputStream file = new FileInputStream("phase2/src/DB/Events.ser");
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);

            events = (BuildingManager) input.readObject();
            input.close();
        } catch (IOException | ClassNotFoundException ex) {
            return events;
        }
        return events;
    }

    /**
     * This method stores a BuildingManager object in Events.ser.
     * @param events A BuildingManager object.
     */
    public void setEvents(BuildingManager events) {
        try {
            OutputStream file = new FileOutputStream("phase2/src/DB/Events.ser");
            OutputStream buffer = new BufferedOutputStream(file);
            ObjectOutput output = new ObjectOutputStream(buffer);

            output.writeObject(events);
            output.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


}
