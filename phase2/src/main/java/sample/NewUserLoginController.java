package main.java.sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NewUserLoginController extends AbstractController implements Initializable {
    @FXML
    Button loginButton;
    @FXML
    Button backButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {}


    /**
     * When the button is clicked, it will check if the username and password matches. Login will be successful
     * if information matches, unsuccessful otherwise.
     * @param event is the action of clicking the "Login" button
     */
    public void handleLoginButton(ActionEvent event){

    }

}
