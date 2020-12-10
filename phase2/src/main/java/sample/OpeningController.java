package main.java.sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import main.java.Controllers.ProgramMainGUI;
import main.java.Gateways.EventGateway;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class OpeningController extends AbstractController implements Initializable {
    public ToggleGroup userType;
    @FXML
    private Button loginButton;
    @FXML
    RadioButton retUser;
    @FXML
    RadioButton newUser;
    @FXML
    private ChoiceBox<String> userRole;
    @FXML
    private Label userRoleLabel;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Label invalidLoginText;

    private ProgramMainGUI sys;

    FXMLLoader loader;


    //reminder: initializer
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        EventGateway eventGateway = new EventGateway();
        this.sys = new ProgramMainGUI(eventGateway.read());
        userRole.getItems().addAll("Organizer", "Attendee");
    }


    public void handleNewUser(ActionEvent event) throws IOException {
        this.userRole.setVisible(true);
        this.userRoleLabel.setVisible(true);
    }
    public void handleRetUser(ActionEvent event) throws IOException {
        this.userRole.setVisible(false);
        this.userRoleLabel.setVisible(false);
    }
    /**
     * When the button is clicked, it will check if the username and password matches. Login will be successful
     * if information matches, unsuccessful otherwise.
     * @param event is the action of clicking the "Login" button
     */
    public void handleLoginButton(ActionEvent event) throws IOException, ClassNotFoundException, NullPointerException {
        //Creates the user and stores the login information of the user and log them in depending on the role.
        if (userType.getSelectedToggle() == newUser && userRole.getValue() != null) {
            sys.register(userRole.getValue(), this.username.getText(), this.password.getText());
        }
        String login_attempt = sys.login(this.username.getText(),this.password.getText());
        if (login_attempt.equalsIgnoreCase("unsuccessful") || userRole.getValue() == null){
            this.invalidLoginText.setVisible(true);
        }
        else{
            helperSceneSwitcher(event, "HomeScreen.fxml"); //TODO:Change menu based on user role
        }

//        System.out.println("ERROR: Type not found when trying to log in.");

    }
//            if (userRole.getSelectionModel().getSelectedItem().equalsIgnoreCase("organizer")){
//                String username;
//                String password;
//                String role;
//
//                role = "organizer";
//                username = "o1";
//                password = "o1";
//                EventGateway eventGateway = new EventGateway();
//                ProgramMainGUI sys = new ProgramMainGUI(eventGateway.read());
//                username = sys.register(role, username, password);
//                helperSceneSwitcher(event, "HomeScreen.fxml");
//            }
//            else if (userRole.getValue().equalsIgnoreCase("attendee")){
//                String username;
//                String password;
//                String role;
//
//                role = "attendee";
//                username = "a1";
//                password = "a1";
//                EventGateway eventGateway = new EventGateway();
//                ProgramMainGUI sys = new ProgramMainGUI(eventGateway.read());
//                username = sys.register(role, username, password);
//
//                helperSceneSwitcher(event, "HomeScreen.fxml");
//            }
//



}
