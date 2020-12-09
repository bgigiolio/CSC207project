package main.java.sample;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import main.java.Gateways.EventGateway;
import main.java.UseCases.BuildingManager;

public class ScheduleController {

    @FXML
    private TextArea scheduleTxt;

    public ScheduleController() {
    }

    @FXML
    private void initialize() {
        scheduleTxt.setText(new EventGateway().read().toString());
    }

    @FXML
    private void downloadSchedule() {
    }

}