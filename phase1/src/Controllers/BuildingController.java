package Controllers;
import UseCases.*;
public class BuildingController {
    public BuildingController(String buildingName) {
        BuildingManager manager = new BuildingManager(buildingName);
    }
}
