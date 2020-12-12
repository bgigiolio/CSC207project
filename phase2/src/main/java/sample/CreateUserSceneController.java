package main.java.sample;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import main.java.Gateways.UserLoginGateway;
import main.java.UseCases.UserManager;

public class CreateUserSceneController {

    private UserManager userManager;

    private String username;

    private String password;

    private String role;

    private UserLoginGateway userLoginGateway;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<String> roleMenu;

    @FXML
    private Text topText;

    @FXML
    private Text userRoleText;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private Text errorText;

    @FXML
    private Button createButton;

    @FXML
    void createAccountPressed(ActionEvent event) {
        if (usernameField.getText() != null && passwordField.getText() != null && roleMenu.getValue() != null){
            this.username = usernameField.getText();
            this.password = passwordField.getText();
            this.role = roleMenu.getValue();
        }else{
            String issue = "";
            if (usernameField.getText() == null){
                issue = "Please enter the username";
            }else if (passwordField.getText() == null){
                issue = "Please enter the password";
            }else if (roleMenu.getValue() == null){
                issue = "Please select a role";
            }
            errorText.setText(issue);
            return;
        }
        if (this.userManager.registerUser(username, password, role)){
            errorText.setText("Account successfully created!");
            this.userLoginGateway.save(this.userManager);
        }else{
            errorText.setText("Account creation failed!");
        }

    }
    public void setUserManager(UserManager userManager){
        this.userManager = userManager;
    }
    public void setUserLoginGateway(UserLoginGateway userLoginGateway){
        this.userLoginGateway = userLoginGateway;
    }
    public void showOptions(){
        roleMenu.getItems().addAll("organizer", "attendee", "speaker", "admin");
    }

    @FXML
    void initialize() {
        assert roleMenu != null : "fx:id=\"roleMenu\" was not injected: check your FXML file 'createUserScene.fxml'.";
        assert topText != null : "fx:id=\"topText\" was not injected: check your FXML file 'createUserScene.fxml'.";
        assert userRoleText != null : "fx:id=\"userRoleText\" was not injected: check your FXML file 'createUserScene.fxml'.";
        assert usernameField != null : "fx:id=\"usernameField\" was not injected: check your FXML file 'createUserScene.fxml'.";
        assert passwordField != null : "fx:id=\"passwordField\" was not injected: check your FXML file 'createUserScene.fxml'.";
        assert errorText != null : "fx:id=\"errorText\" was not injected: check your FXML file 'createUserScene.fxml'.";
        assert createButton != null : "fx:id=\"createButton\" was not injected: check your FXML file 'createUserScene.fxml'.";
        Platform.runLater(this::showOptions);
    }
}
