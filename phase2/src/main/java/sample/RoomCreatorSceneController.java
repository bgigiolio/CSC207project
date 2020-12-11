package main.java.sample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class RoomCreatorSceneController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField startTimePrompt;

    @FXML
    private TextField durationPrompt;

    @FXML
    private TextField eventTitlePrompt;

    @FXML
    private TextField capacityPrompt;

    @FXML
    private Text errorText;

    @FXML
    private Button createButton;

    @FXML
    void createButtonPressed(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert datePicker != null : "fx:id=\"datePicker\" was not injected: check your FXML file 'RoomCreatorScene.fxml'.";
        assert startTimePrompt != null : "fx:id=\"startTimePrompt\" was not injected: check your FXML file 'RoomCreatorScene.fxml'.";
        assert durationPrompt != null : "fx:id=\"durationPrompt\" was not injected: check your FXML file 'RoomCreatorScene.fxml'.";
        assert eventTitlePrompt != null : "fx:id=\"eventTitlePrompt\" was not injected: check your FXML file 'RoomCreatorScene.fxml'.";
        assert capacityPrompt != null : "fx:id=\"capacityPrompt\" was not injected: check your FXML file 'RoomCreatorScene.fxml'.";
        assert errorText != null : "fx:id=\"errorText\" was not injected: check your FXML file 'RoomCreatorScene.fxml'.";
        assert createButton != null : "fx:id=\"createButton\" was not injected: check your FXML file 'RoomCreatorScene.fxml'.";

    }
}
