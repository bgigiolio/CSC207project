package main.java;

import main.java.Controllers.ProgramMain;
import main.java.Gateways.EventGateway;

import java.io.IOException;

public class Run {
    public static void main(String[] args) throws ClassNotFoundException, IOException {

        try(ProgramMain sys = new ProgramMain()){
            sys.start();
        }
    }
}
