import Controllers.BuildingController;
import Controllers.InitialPrompt;

import java.io.IOException;

public class Run {
    public static void main(String[] args) throws IOException {
        BuildingController controller = new BuildingController("Building");
        InitialPrompt run = new InitialPrompt(controller.getBuilding());
        run.startProgram();
    }
}
