package Controllers;


import Gateways.EventGateway;
import UseCases.BuildingManager;

/**
 * <h1>ImportExportEventController</h1>
 * This Controller class is responsible for interacting with the EventGateway to retrieve
 * and store a copy of the building.
 * This should only be called by other controllers.
 * @author Zachary Werle
 */
public class ImportExportEventController {

    private final EventGateway g = new EventGateway();

    /**
     * This method calls EventGateway to retrieve a BuildingManager object.
     * @return A BuildingManager object.
     * @throws ClassNotFoundException Handles the case where no serializable class is found.
     */
    public BuildingManager importEvents() throws ClassNotFoundException{
        return g.getEvents();
    }

    /**
     * This method calls EventGateway to store a BuildingManager object.
     * @param events A BuildingManager object.
     */
    public void exportEvents(BuildingManager events){
        g.setEvents(events);
    }

}
