package Controllers;
import UseCases.*;
public class BuildingController {
    private final BuildingManager building;
    public BuildingController(String buildingName) {
        this.building = new BuildingManager(buildingName);
    }
    public BuildingController(BuildingManager building){
        this.building = building;
    }
    public String getReadout(){
        return this.building.toString();
    }
    public BuildingManager getBuilding(){
        return this.building;
    }
}
