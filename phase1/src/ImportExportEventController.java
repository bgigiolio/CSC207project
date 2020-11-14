import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class ImportExportEventController {

    private final EventGateway g = new EventGateway();

    public HashMap<LocalDate, ArrayList<Event>> importEvents() throws ClassNotFoundException{
        return g.getEvents();
    }

    public void exportEvents(HashMap<LocalDate, ArrayList<Event>> events){
        g.setEvents(events);
    }

}
