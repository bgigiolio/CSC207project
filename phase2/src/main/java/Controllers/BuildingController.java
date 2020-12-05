package main.java.Controllers;
import main.java.UseCases.*;

/**
 * <h1>BuildingController</h1>
 * This controller class is responsible for calling the necessary methods in BuildingManager to manage th information of a building.
 * This should only interact with the BuildingManager class and AttendeeMenuController.
 * @author Zachary Werle
 * @version Phase 1
 */
public class BuildingController {
    private final BuildingManager building;

    /**
     * This constructor is responsible for creating an instance of BuildingController with specified buildingName.
     * @param buildingName The name of the building.
     */
    public BuildingController(String buildingName) {
        this.building = new BuildingManager(buildingName);
    }

    /**
     * This constructor is responsible for creating an instance of BuildingController with a given building
     * @param building The instance of BuildingManager.
     */
    public BuildingController(BuildingManager building){
        this.building = building;
    }

    /**
     * This method is responsible for returning a String representation of BuildingController.
     * @return The String object representing the BuildingController.
     */
    public String getReadout(){
        return this.building.toString();
    }

    /**
     * This method is responsible for returning an instance of the building.
     * @return A BuildingManager object.
     */
    public BuildingManager getBuilding(){
        return this.building;
    }

    /**
     * This method is responsible for adding a room to the building.
     * @return A boolean that shows if the room was added or not.
     */
    public boolean addRoom(String name, int startHour, int endHour, int roomCapacity){
        return this.building.addRoom1(name, startHour, endHour, roomCapacity);
    }
}
