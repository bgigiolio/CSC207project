package main.java.sample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class signUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField EventID;

    @FXML
    private Text printSchedule;

    @FXML
    private Text errorMessageText;

    @FXML
    void initialize() {
        assert EventID != null : "fx:id=\"EventID\" was not injected: check your FXML file 'Untitled'.";
        assert printSchedule != null : "fx:id=\"printSchedule\" was not injected: check your FXML file 'Untitled'.";
        assert errorMessageText != null : "fx:id=\"errorMessageText\" was not injected: check your FXML file 'Untitled'.";
    }

}
