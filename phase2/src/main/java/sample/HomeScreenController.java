package main.java.sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.java.Gateways.EventGateway;
import main.java.Gateways.UserLoginGateway;
import main.java.UseCases.BuildingManager;
import main.java.UseCases.UserManager;

import java.io.IOException;

public class HomeScreenController implements AutoCloseable{
    @FXML
    private AnchorPane mainPane;

    private String user;

    public BuildingManager buildingManager;
    UserLoginGateway userLoginGateway;
    public UserManager userManager;
    EventGateway eventGateway;
    private String username;
    private String password;
    private String role;

    private Scene openingScene;
    private Stage primaryStage;

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

    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setOpeningScene(Scene openingScene){
        this.openingScene = openingScene;
    }
    public void setPrimaryStage(Stage primaryStage){
        this.primaryStage = primaryStage;
    }

    public void initialize(){
        this.eventGateway = new EventGateway();
        this.userLoginGateway = new UserLoginGateway();
        this.buildingManager = eventGateway.read();
        this.userManager = userLoginGateway.read();
        welcomeText.setText("Welcome " + this.username + "!");


    }

    /**
     * Register a new user
     * @param role user's roles
     * @return the username of the registered user
     */
    public String register(String role, String username, String password){

        if (this.userManager.registerUser(username, password, role)) {
            this.userLoginGateway.save(this.userManager);
            return username;
        }
        return "invalid";

    }
    /**
     * This is how a user will log in. Here we call the log in menu prompt.
     * @return username of user logged. Null if couldn't log in.
     */
    public String login(String username, String password) {


        if (this.userManager.loginUser(username, password).equalsIgnoreCase("loggedin")) {
            return username;
        }
        return "invalid";
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
    private MenuItem Message;

    @FXML
    private MenuItem manageFriendsList;

    @FXML
    private MenuItem logout;

    @FXML
    private AnchorPane bottomPane;

    @FXML
    private Text toPrint;



    @FXML
    void cancel(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("signUpScene.fxml"));
        loader.load();
        signUpController SUC = loader.getController();
        SUC.setBuilding(this.buildingManager);
    }

    @FXML
    void logout(ActionEvent event) throws IOException {
        Stage primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("OpeningScene.fxml"));
        primaryStage = (Stage) menuOptions.getScene().getWindow();
        Scene openingScene = new Scene(root,300,275);
        primaryStage.setScene(openingScene);
        primaryStage.show();

    }

    //under work
    @FXML
    void manageFriends(ActionEvent event) throws IOException {
        Stage friendsStage;

        Parent root = FXMLLoader.load(getClass().getResource("friendsScene.fxml"));
        friendsStage = new Stage();
        friendsStage.setScene(new Scene(root,500,500));
        friendsStage.show();
    }


    @FXML
    void reviewSchedule(ActionEvent event) {
        //wait what does this even do?
    }

    @FXML
    void Message(ActionEvent event) throws IOException {
        Stage messageStage;

        Parent root = FXMLLoader.load(getClass().getResource("MessageScene.fxml"));
        messageStage = new Stage();
        messageStage.setScene(new Scene(root,500,500));
        messageStage.show();
    }


    @FXML
    void showSchedule(ActionEvent event) {
        toPrint.setText(this.buildingManager.toString());
    }

    @FXML
    void signUp(ActionEvent event) throws IOException {
        Stage EventStage;
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("signUpScene.fxml"));
        loader.load();
        signUpController SUC = loader.getController();
        SUC.setBuilding(this.buildingManager);
        Parent root = FXMLLoader.load(getClass().getResource("signUpScene.fxml"));
        EventStage = new Stage();
        EventStage.setScene(new Scene(root,500,500));
        EventStage.show();
    }

    @Override
    public void close(){
        System.out.println("Inside constructor");
        new UserLoginGateway().save(userManager);
        new EventGateway().save(buildingManager);
    }
}
