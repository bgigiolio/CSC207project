package main.java.sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.java.UseCases.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class friendsController {
    private UserManager userManager;
    private String username;


    @FXML
    RadioButton addFriendButton;

    @FXML
    RadioButton removeFriendButton;

    @FXML
    private Button actionButton;

    @FXML
    private Text errorMessageText;

    @FXML
    private TextField friendUsername;

    @FXML
    void addFriendSelected(ActionEvent event) {
        actionButton.setText("Add friend");
    }

    @FXML
    void removeFriendSelected(ActionEvent event) {
        actionButton.setText("Remove friend");
    }

    public void setUserManager(UserManager userManager){
        this.userManager = userManager;
    }

    public void setUsername(String username){
        this.username = username;
    }

    @FXML
    void initialize() throws IOException {

        assert friendUsername != null : "fx:id=\"friendUsername\" was not injected: check your FXML file 'Untitled'.";
        assert errorMessageText != null : "fx:id=\"errorMessageText\" was not injected: check your FXML file 'Untitled'.";
        assert addFriendButton != null : "fx:id=\"addFriendButton\" was not injected: check your FXML file 'Untitled'.";
        assert removeFriendButton != null : "fx:id=\"removeFriendButton\" was not injected: check your FXML file 'Untitled'.";
        assert actionButton != null : "fx:id=\"actionButton\" was not injected: check your FXML file 'Untitled'.";

    }

}