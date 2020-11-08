import java.io.*;
import java.util.ArrayList;

public class EventGateway implements Serializable {

    public ArrayList<Event> getEvents() throws ClassNotFoundException {
        ArrayList<Event> events = new ArrayList<>();
        try {
            InputStream file = new FileInputStream("Events.txt");
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);

            events.add((Event) input.readObject());
            input.close();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        try {
            OutputStream file = new FileOutputStream("Events.txt");
            OutputStream buffer = new BufferedOutputStream(file);
            ObjectOutput input = new ObjectOutputStream(buffer);

            for (Event e: events){
                input.writeObject(e);
            }
            input.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
