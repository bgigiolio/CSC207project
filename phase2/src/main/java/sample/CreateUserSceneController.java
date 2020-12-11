package main.java.sample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class CreateUserSceneController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<?> roleMenu;

    @FXML
    private Text topText;

    @FXML
    private Text userRoleText;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private Text errorText;

    @FXML
    private Button createButton;

    @FXML
    void createAccountPressed(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert roleMenu != null : "fx:id=\"roleMenu\" was not injected: check your FXML file 'createUserScene.fxml'.";
        assert topText != null : "fx:id=\"topText\" was not injected: check your FXML file 'createUserScene.fxml'.";
        assert userRoleText != null : "fx:id=\"userRoleText\" was not injected: check your FXML file 'createUserScene.fxml'.";
        assert usernameField != null : "fx:id=\"usernameField\" was not injected: check your FXML file 'createUserScene.fxml'.";
        assert passwordField != null : "fx:id=\"passwordField\" was not injected: check your FXML file 'createUserScene.fxml'.";
        assert errorText != null : "fx:id=\"errorText\" was not injected: check your FXML file 'createUserScene.fxml'.";
        assert createButton != null : "fx:id=\"createButton\" was not injected: check your FXML file 'createUserScene.fxml'.";

    }
}
