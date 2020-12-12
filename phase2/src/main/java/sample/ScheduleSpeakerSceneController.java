package main.java.sample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class ScheduleSpeakerSceneController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField speakerNameField;

    @FXML
    private TextField eventUUID;

    @FXML
    private Text errorText;

    @FXML
    private Button actionButton;

    @FXML
    void actionButtonPressed(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert speakerNameField != null : "fx:id=\"speakerNameField\" was not injected: check your FXML file 'scheduleSpeakerScene.fxml'.";
        assert eventUUID != null : "fx:id=\"eventUUID\" was not injected: check your FXML file 'scheduleSpeakerScene.fxml'.";
        assert errorText != null : "fx:id=\"errorText\" was not injected: check your FXML file 'scheduleSpeakerScene.fxml'.";
        assert actionButton != null : "fx:id=\"actionButton\" was not injected: check your FXML file 'scheduleSpeakerScene.fxml'.";

    }
}
