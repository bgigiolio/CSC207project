package main.java.sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import main.java.UseCases.*;

public class signUpController {

    private BuildingManager building;

    private String username;

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
    private RadioButton signUpButton;

    @FXML
    private ToggleGroup eventChoice;

    @FXML
    private RadioButton leaveEventButton;

    @FXML
    private Button actionButton;

    @FXML
    void actionButtonPressed(ActionEvent event) {

    }

    @FXML
    void leaveEventSelected(ActionEvent event) {
        actionButton.setText("Leave Event");
    }

    @FXML
    void signUpSelected(ActionEvent event) {
        actionButton.setText("Sign Up");
    }

    public void setBuilding(BuildingManager building){
        this.building = building;
    }

    public void setUsername(String username){
        this.username = username;
    }

    @FXML
    void initialize() throws IOException {
        assert EventID != null : "fx:id=\"EventID\" was not injected: check your FXML file 'Untitled'.";
        assert printSchedule != null : "fx:id=\"printSchedule\" was not injected: check your FXML file 'Untitled'.";
        assert errorMessageText != null : "fx:id=\"errorMessageText\" was not injected: check your FXML file 'Untitled'.";
        assert signUpButton != null : "fx:id=\"signUpButton\" was not injected: check your FXML file 'Untitled'.";
        assert eventChoice != null : "fx:id=\"eventChoice\" was not injected: check your FXML file 'Untitled'.";
        assert leaveEventButton != null : "fx:id=\"leaveEventButton\" was not injected: check your FXML file 'Untitled'.";
        assert actionButton != null : "fx:id=\"actionButton\" was not injected: check your FXML file 'Untitled'.";

    }
}
