package main.java.sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import main.java.Gateways.EventGateway;
import main.java.Gateways.UserLoginGateway;
import main.java.UseCases.BuildingManager;
import main.java.UseCases.UserManager;

public class HomeScreenController {
    @FXML
    private AnchorPane mainPane;

    private String user;

    public final BuildingManager buildingManager;

    public final UserManager userManager;

//    public HomeScreenController(String username){
//        this.user = username;
//        EventGateway eventGateway = new EventGateway();
//        UserLoginGateway userLoginGateway = new UserLoginGateway();
//        this.buildingManager = eventGateway.read();
//        this.userManager = userLoginGateway.read();
//        welcomeText.setText("Welcome" + this.user);
//
//        String role = "attendee";
//        main.java.Controllers.AttendeeMenuController AMC = new main.java.Controllers.AttendeeMenuController(username, role, this.buildingManager, this.userManager);
//
//    }
    public HomeScreenController(){
        EventGateway eventGateway = new EventGateway();
        UserLoginGateway userLoginGateway = new UserLoginGateway();
        this.buildingManager = eventGateway.read();
        this.userManager = userLoginGateway.read();
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("OpeningScene.fxml"));
        OpeningController openingController = loader.getController();

        String username = openingController.getUserInfo().get(0);
        String role = openingController.getUserInfo().get(2);
        main.java.Controllers.AttendeeMenuController AMC = new main.java.Controllers.AttendeeMenuController(username, role, this.buildingManager, this.userManager);
    }

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
