package main.java;

import main.java.Controllers.ProgramMain;
import main.java.Gateways.EventGateway;

import java.io.IOException;

public class Run {
    public static void main(String[] args) throws ClassNotFoundException, IOException {

        EventGateway eventGateway = new EventGateway();

        try(ProgramMain sys = new ProgramMain(eventGateway.read())){
            sys.start();
        }
    }
}
