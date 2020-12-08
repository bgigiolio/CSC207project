package main.java.Controllers;


import main.java.Gateways.EventGateway;
import main.java.UseCases.BuildingManager;

/**
 * <h1>ImportExportEventController</h1>
 * This Controller class is responsible for interacting with the EventGateway to retrieve
 * and store a copy of the building.
 * This should only be called by other controllers.
 * @author Zachary Werle
 * @version Phase 1
 */
public class ImportExportEventController {

    private final EventGateway gateway = new EventGateway();

    /**
     * This method calls EventGateway to retrieve a BuildingManager object.
     * @return A BuildingManager object.
     */
    public BuildingManager importEvents(){
        return gateway.read();
    }

    /**
     * This method calls EventGateway to store a BuildingManager object.
     * @param events A BuildingManager object.
     */
    public void exportEvents(BuildingManager events){
        gateway.save(events);
    }

}
