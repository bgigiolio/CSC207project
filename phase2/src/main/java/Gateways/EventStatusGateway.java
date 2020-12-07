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
public class EventStatusGateway {

    /**
     * The system to be load or save to a file.
     */
    private EventStatus eventStatus = new EventStatus();

    public EventStatus getEventStatus() {
        return eventStatus;
    }

    /**
     * Set EventStatus.
     *
     * @param eventStatus the EventStatus object that stores the data about event registrations of users
     */
    public void setEventStatus(EventStatus eventStatus) {
        this.eventStatus = eventStatus;
    }

    /**
     * Load filepath to controller.
     *
     * @return eventStatus the EventStatus object with existing data about event registrations of users
     */
    public EventStatus loadFromFile(String filePath){
        try{
            InputStream file = new FileInputStream(filePath);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);

            eventStatus = ((EventStatus) input.readObject());
            input.close();
        } catch (EOFException e){
            eventStatus = new EventStatus();
        } catch (ClassNotFoundException | StreamCorruptedException e) {
            System.err.println("Corrupted file contents in event database. Clearing file...");
            clearFileContentsUtil(filePath);
            eventStatus = new EventStatus();
        }catch (IOException e) {  //other IO exception
            System.err.println("Unknown error when reading from event database file.");
            e.printStackTrace();
            eventStatus = new EventStatus();
        }
        return eventStatus;
    }

    /**
     * Save eventStatus to filepath.
     *
     * @param filePath the name of the file
     */
    public void saveToFile(String filePath){
        try {
            OutputStream file = new FileOutputStream(filePath);
            OutputStream buffer = new BufferedOutputStream(file);
            ObjectOutput output = new ObjectOutputStream(buffer);

            output.writeObject(eventStatus);
            output.close();
        }
        catch (IOException e) {
            System.err.println("Could not save event data to file.");
            e.printStackTrace();
        }
    }

    /**
     * Utility method to clear file contents if file contains corrupt data
     */
    private void clearFileContentsUtil(String dbPath) {
        try {
            PrintWriter writer = new PrintWriter(dbPath);
            writer.print("");
            writer.close();
        } catch (FileNotFoundException e) {
            System.err.println("Unexpected error when accessing the event database file.");
            e.printStackTrace();
        }
    }
}
