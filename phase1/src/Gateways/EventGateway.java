package Gateways;

import UseCases.BuildingManager;
import UseCases.Schedule;
import java.io.*;

public class EventGateway implements Serializable {

    public BuildingManager getEvents() throws ClassNotFoundException {
        BuildingManager events = new BuildingManager("0");
        try {
            InputStream file = new FileInputStream("phase1/src/DB/Events.ser");
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);

            events = (BuildingManager) input.readObject();
            input.close();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return events;
    }

    public void setEvents(BuildingManager events) {
        try {
            OutputStream file = new FileOutputStream("phase1/src/DB/Events.ser");
            OutputStream buffer = new BufferedOutputStream(file);
            ObjectOutput output = new ObjectOutputStream(buffer);

            output.writeObject(events);
            output.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


}
