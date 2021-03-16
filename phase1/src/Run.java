import Controllers.BuildingController;
import Controllers.InitialPrompt;

import java.io.IOException;

public class Run {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        BuildingController controller = new BuildingController("Building");
        //If we serialized the building class, we will want to load the serialized building here
        InitialPrompt run = new InitialPrompt(controller.getBuilding());
        run.startProgram();
    }
}
