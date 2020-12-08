package main.java.Gateways;

import main.java.UseCases.EventStatus;

import java.io.*;

/**
 * <h1>Event Status Gateway</h1>
 * Loads file about the system responsible for storing a hashmap that maps a user's username
 * to a List of event title that user had signed up for, or save it to file.
 *
 * @author Morgan Chang
 */
public class EventStatusGateway extends DatabaseGateway<EventStatus> {

    public EventStatusGateway() {
        super("phase2/src/main/java/DB/EventStatusData.ser");
    }

    /**
     * Load object from database to controller.
     *
     * @return eventStatus the EventStatus object with existing data about event registrations of users
     */
    @Override
    public EventStatus read() {
        EventStatus eventStatus;
        try {
            InputStream file = new FileInputStream(getDbPath());
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);

            eventStatus = ((EventStatus) input.readObject());
            input.close();
        } catch (EOFException e) {
            eventStatus = new EventStatus();
        } catch (ClassNotFoundException | StreamCorruptedException | InvalidClassException e) {
            System.err.println("Corrupted file contents in event status database. Clearing file...");
            clearFileContentsUtil(getDbPath());
            eventStatus = new EventStatus();
        } catch (IOException e) {  //other IO exception
            System.err.println("Unknown error when reading from event status database file.");
            e.printStackTrace();
            eventStatus = new EventStatus();
        }
        return eventStatus;
    }

    /**
     * Save eventStatus to filepath.
     *
     * @param obj The object to save
     */
    @Override
    public void save(EventStatus obj) {
        clearFileContentsUtil("event status");
        try {
            OutputStream file = new FileOutputStream(getDbPath());
            OutputStream buffer = new BufferedOutputStream(file);
            ObjectOutput output = new ObjectOutputStream(buffer);

            output.writeObject(obj);
            output.close();
        } catch (IOException e) {
            System.err.println("Could not save event status data to file.");
            e.printStackTrace();
        }
    }
}
