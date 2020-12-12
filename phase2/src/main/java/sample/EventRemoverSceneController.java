package main.java.sample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class EventRemoverSceneController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text eventRemovedText;

    @FXML
    private TextField UUIDField;

    @FXML
    private Button actionButton;

    @FXML
    private Text errorText;

    @FXML
    void actionButtonPressed(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert eventRemovedText != null : "fx:id=\"eventRemovedText\" was not injected: check your FXML file 'EventRemoverScene.fxml'.";
        assert UUIDField != null : "fx:id=\"UUIDField\" was not injected: check your FXML file 'EventRemoverScene.fxml'.";
        assert actionButton != null : "fx:id=\"actionButton\" was not injected: check your FXML file 'EventRemoverScene.fxml'.";
        assert errorText != null : "fx:id=\"errorText\" was not injected: check your FXML file 'EventRemoverScene.fxml'.";

    }
}
