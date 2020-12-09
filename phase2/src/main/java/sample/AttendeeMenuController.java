package main.java.sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class AttendeeMenuController {
    @FXML
    private AnchorPane mainPane;

    private String user;

    @FXML
    private SplitPane multiPane;

    @FXML
    private AnchorPane topPane;

    @FXML
    private Text welcomeText;

    @FXML
    private Text instructionPrompt;

    @FXML
    private MenuButton menuOptions;

    @FXML
    private MenuItem seeEventSchedule;

    @FXML
    private MenuItem reviewEventSchedule;

    @FXML
    private MenuItem signUpForEvent;

    @FXML
    private MenuItem cancelEvent;

    @FXML
    private MenuItem sendMessage;

    @FXML
    private MenuItem reviewMessages;

    @FXML
    private MenuItem manageFriendsList;

    @FXML
    private MenuItem logout;

    @FXML
    private AnchorPane bottomPane;

    @FXML
    private Text toPrint;
    public AttendeeMenuController(String user){
        this.user = user;
        welcomeText.setText("Welcome" + this.user);
    }
    public AttendeeMenuController(){
//        this.user = "";
//        welcomeText.setText("Welcome" + this.user);
    }
    @FXML
    void cancel(ActionEvent event) {

    }

    @FXML
    void logout(ActionEvent event) {

    }

    @FXML
    void manageFriends(ActionEvent event) {

    }

    @FXML
    void reviewMessage(ActionEvent event) {

    }

    @FXML
    void reviewSchedule(ActionEvent event) {

    }

    @FXML
    void send(ActionEvent event) {

    }

    @FXML
    void showSchedule(ActionEvent event) {

    }

    @FXML
    void signUp(ActionEvent event) {

    }

}
