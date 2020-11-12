import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class EventGateway implements Serializable {

    public HashMap<LocalDate, ArrayList<Event>> getEvents() throws ClassNotFoundException {
        HashMap<LocalDate, ArrayList<Event>> events = new HashMap<>();
        try {
            InputStream file = new FileInputStream("Events.txt");
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);

            events = (HashMap<LocalDate, ArrayList<Event>>) input.readObject();
            input.close();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return events;
    }

    public void setEvents(HashMap<LocalDate, ArrayList<Event>> events) {
        try {
            OutputStream file = new FileOutputStream("Events.txt");
            OutputStream buffer = new BufferedOutputStream(file);
            ObjectOutput output = new ObjectOutputStream(buffer);

            output.writeObject(events);
            output.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


}
