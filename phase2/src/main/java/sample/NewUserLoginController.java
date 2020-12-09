package main.java.sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import main.java.Controllers.*;
import main.java.Controllers.AttendeeMenuController;
import main.java.Gateways.EventGateway;

public class NewUserLoginController extends AbstractController implements Initializable {
    @FXML
    private Button loginButton;
    @FXML
    private Button backButton;
    @FXML
    private ChoiceBox<String> userType;

// youtube jared wright
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userType.getItems().addAll("Organizer", "Attendee");
    }


    /**
     * When the button is clicked, it will check if the username and password matches. Login will be successful
     * if information matches, unsuccessful otherwise.
     * @param event is the action of clicking the "Login" button
     */
    public void handleLoginButton(ActionEvent event) throws IOException, ClassNotFoundException, NullPointerException {
        //Creates the user and stores the login information of the user and log them in depending on the role.

        if (userType.getValue().equalsIgnoreCase("organizer")){
            String username;
            String password;
            String role;

            role = "organizer";
            username = "o1";
            password = "o1";
            EventGateway eventGateway = new EventGateway();
            ProgramMainGUI sys = new ProgramMainGUI(eventGateway.read());
            username = sys.register(role, username, password);
            helperButtonHandler(event, "AttendeeMenu.fxml");
        }
        else if (userType.getValue().equalsIgnoreCase("attendee")){
            String username;
            String password;
            String role;

            role = "attendee";
            username = "a1";
            password = "a1";
            EventGateway eventGateway = new EventGateway();
            ProgramMainGUI sys = new ProgramMainGUI(eventGateway.read());
            username = sys.register(role, username, password);

            helperButtonHandler(event, "AttendeeMenu.fxml");
        }
        else{
            System.out.println("ERROR: Type not found when trying to log in.");
        }

    }

}
