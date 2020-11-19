package Controllers;


import Gateways.EventGateway;
import UseCases.BuildingManager;

import java.time.LocalDateTime;

public class ImportExportEventController {

    private final EventGateway g = new EventGateway();

    public BuildingManager importEvents() throws ClassNotFoundException{
        return g.getEvents();
    }

    public void exportEvents(BuildingManager events){
        g.setEvents(events);
    }

}
