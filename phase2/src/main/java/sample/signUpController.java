package main.java.sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

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

    private UserManager userManager;

    @FXML
    private ResourceBundle resources;

    private boolean signup;

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
        UUID id;
        try{
            id = UUID.fromString(EventID.getText());
        }catch(IllegalArgumentException e){
            errorMessageText.setText("Invalid UUID format");
            errorMessageText.setStyle("-fx-text-fill: red; -fx-font-size: 16px;");
            return;
        }
        if (signup){
            if (userManager.signUpForEvent(username, id) & building.addAttendee(username, id)){
                errorMessageText.setText("You are now attending ..."); // add to
                errorMessageText.setStyle("-fx-text-fill: green; -fx-font-size: 16px;");
            }else{
                errorMessageText.setText("Event with UUID: " + id.toString() + " does not exist or is full.");
                errorMessageText.setStyle("-fx-text-fill: red; -fx-font-size: 16px;");
            }
        }else{
            if (userManager.cancelEnrollment(username, id) & building.removeAttendee(username, id)){
                errorMessageText.setText("You have successfully left ...");
                errorMessageText.setStyle("-fx-text-fill: green; -fx-font-size: 16px;");
            }else{
                errorMessageText.setText("Event with UUID: " + id.toString() +
                        " does not exist or you are not attending");
                errorMessageText.setStyle("-fx-text-fill: red; -fx-font-size: 16px;");

            }
        }

    }

    @FXML
    void leaveEventSelected(ActionEvent event) {
        actionButton.setText("Leave Event");
        signup = false;
    }

    @FXML
    void signUpSelected(ActionEvent event) {
        actionButton.setText("Sign Up");
        signup = true;
    }

    public void setBuilding(BuildingManager building){
        this.building = building;
    }

    public void setUserManager(UserManager userManager){
        this.userManager = userManager;
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
