package main.java;

import main.java.Controllers.ImportExportEventController;
import main.java.Controllers.ProgramMain;


public class Run {
    public static void main(String[] args) throws ClassNotFoundException {

        ImportExportEventController eventGateway = new ImportExportEventController();

        ProgramMain sys = new ProgramMain(eventGateway.importEvents());

        sys.start();

//         Main gui = new Main();
//         gui.launch();
    }
}
