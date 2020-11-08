import java.util.ArrayList;

public class ImportExportEventController {

    private final EventGateway g = new EventGateway();

    public ArrayList<Event> importEvents() throws ClassNotFoundException{
        return g.getEvents();
    }

    public void exportEvents(ArrayList<Event> events){
        g.setEvents(events);
    }

}
