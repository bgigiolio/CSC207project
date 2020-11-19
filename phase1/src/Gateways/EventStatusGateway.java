package Gateways;

import Entities.Event;
import UseCases.EventStatus;
import UseCases.LoginUserManager;

import java.io.*;

public class EventStatusGateway {

    private EventStatus eventStatus;

    public EventStatusGateway() {
        this.eventStatus = new EventStatus();
    }

    public EventStatus getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(EventStatus eventStatus) {
        this.eventStatus = eventStatus;
    }

    public EventStatus loadFromFile(String filePath) throws IOException {
        try{
            InputStream file = new FileInputStream(filePath);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);

            eventStatus = ((EventStatus) input.readObject());
            input.close();
        } catch (FileNotFoundException | ClassNotFoundException | EOFException | InvalidClassException e){
            this.saveToFile(filePath);
        }
        return eventStatus;
    }

    public void saveToFile(String filePath) throws IOException {
        try {
            OutputStream file = new FileOutputStream(filePath);
            OutputStream buffer = new BufferedOutputStream(file);
            ObjectOutput output = new ObjectOutputStream(buffer);

            output.writeObject(eventStatus);
            output.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
