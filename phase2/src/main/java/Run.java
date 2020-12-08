package main.java;

import main.java.Controllers.ImportExportEventController;
import main.java.Controllers.ProgramMain;
import main.java.Entities.Event;
import main.java.Gateways.EventGateway;

import java.io.IOException;


public class Run {
    public static void main(String[] args) throws ClassNotFoundException, IOException {

        EventGateway eventGateway = new EventGateway();

        ProgramMain sys = new ProgramMain(eventGateway.read());

        sys.start();

//         Main gui = new Main();
//         gui.launch();
    }
}
