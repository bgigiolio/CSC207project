package main.java.sample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class EventCreatorSceneController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField eventTitlePrompt;

    @FXML
    private TextField roomNamePrompt;

    @FXML
    private DatePicker eventDatePrompt;

    @FXML
    private TextField startHourField;

    @FXML
    private TextField startMinuteField;

    @FXML
    private TextField endHourField;

    @FXML
    private TextField endMinuteField;

    @FXML
    private Button createEventButton;

    @FXML
    private Text errorText;

    @FXML
    void createEventButtonPressed(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert eventTitlePrompt != null : "fx:id=\"eventTitlePrompt\" was not injected: check your FXML file 'EventCreatorScene.fxml'.";
        assert roomNamePrompt != null : "fx:id=\"roomNamePrompt\" was not injected: check your FXML file 'EventCreatorScene.fxml'.";
        assert eventDatePrompt != null : "fx:id=\"eventDatePrompt\" was not injected: check your FXML file 'EventCreatorScene.fxml'.";
        assert startHourField != null : "fx:id=\"startHourField\" was not injected: check your FXML file 'EventCreatorScene.fxml'.";
        assert startMinuteField != null : "fx:id=\"startMinuteField\" was not injected: check your FXML file 'EventCreatorScene.fxml'.";
        assert endHourField != null : "fx:id=\"endHourField\" was not injected: check your FXML file 'EventCreatorScene.fxml'.";
        assert endMinuteField != null : "fx:id=\"endMinuteField\" was not injected: check your FXML file 'EventCreatorScene.fxml'.";
        assert createEventButton != null : "fx:id=\"createEventButton\" was not injected: check your FXML file 'EventCreatorScene.fxml'.";
        assert errorText != null : "fx:id=\"errorText\" was not injected: check your FXML file 'EventCreatorScene.fxml'.";

    }
}
