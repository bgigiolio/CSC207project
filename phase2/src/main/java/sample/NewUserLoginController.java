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
import main.java.Gateways.UserLoginGateway;
import main.java.Presenters.*;
import main.java.UseCases.BuildingManager;
import main.java.UseCases.UserManager;
import main.java.Gateways.EventGateway;
import main.java.Gateways.UserLoginGateway;

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
    public void handleLoginButton(ActionEvent event) throws IOException, ClassNotFoundException {
        //Creates the user and stores the login information of the user and log them in depending on the role.

        if (userType.getValue().equalsIgnoreCase("organizer")){
        }
        else if (userType.getValue().equalsIgnoreCase("attendee")){
            EventGateway eventGateway = new EventGateway();
            main.java.Controllers.AttendeeMenuController currentSession;
            StartingMenu menuPresenter = new StartingMenu();
            UserLoginGateway userLoginGateway = new UserLoginGateway();
            String username;
            String role;
            String userType;
            boolean didQuit;

            do {
                do{
                    menuPresenter.initialPrompt();
                    userType = askUserType();

                    if(userType.equals("N")) {
                        role = askRole();
                        username = register(role);
                    }else {
                        username = login();
                        role = userManager.getUserRole(username);
                    }
                } while(username == null);

                currentSession = new AttendeeMenuController(username, role, buildingManager, userManager);

                currentSession.homepage();
                didQuit = currentSession.menuSelection();
                userLoginGateway.save(userManager);
            } while(!didQuit);
        }
        else{
            System.out.println("ERROR: Type not found when trying to log in.");
        }

    }

}
