import Controllers.BuildingController;
import Controllers.ImportExportEventController;
import Controllers.InitialPrompt;

import java.io.IOException;

public class Run {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //If we serialized the building class, we will want to load the serialized building here
        ImportExportEventController eventGateway = new ImportExportEventController();
        InitialPrompt run = new InitialPrompt(eventGateway.importEvents());
        run.startProgram();
    }
}
