package main.java.Gateways;

import main.java.UseCases.BuildingManager;

import java.io.*;

/**
 * <h1>EventGateway</h1>
 * This Gateway class is responsible for retrieving and storing a copy of the building in the Events.ser file.
 * This should only be called by the ImportExportController.
 * @author Zachary Werle
 * @version Phase 2
 */
public class EventGateway extends DatabaseGateway<BuildingManager>{

    public EventGateway() { super("phase2/src/main/java/DB/Events.ser"); }

    /**
     * This method retrieves a BuildingManager object from Events.ser.
     * @return A BuildingManager object.
     */
    @Override
    public BuildingManager read() {
        BuildingManager events;
        try {
            InputStream file = new FileInputStream(getDbPath());
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);

            events = (BuildingManager) input.readObject();
            input.close();
        } catch (EOFException e) { //database file is empty
            events = new BuildingManager("Building");
        } catch (ClassNotFoundException | StreamCorruptedException e) {   //incorrect class format
            System.err.println("Corrupted file contents in event database. Clearing file...");
            clearFileContentsUtil("event");
            events = new BuildingManager("Building");
        } catch (IOException e) {  //other IO exception
            System.err.println("Unknown error when reading from events database file.");
            e.printStackTrace();
            events = new BuildingManager("Building");
        }
        return events;
    }

    /**
     * This method stores a BuildingManager object in Events.ser.
     * @param obj A BuildingManager object.
     */
    @Override
    public void save(BuildingManager obj) {
        clearFileContentsUtil("event");
        try {
            OutputStream file = new FileOutputStream(getDbPath());
            OutputStream buffer = new BufferedOutputStream(file);
            ObjectOutput output = new ObjectOutputStream(buffer);

            output.writeObject(obj);
            output.close();
        } catch (IOException ex) {
            System.err.println("Could not save event data to database.");
            ex.printStackTrace();
        }
    }

}
