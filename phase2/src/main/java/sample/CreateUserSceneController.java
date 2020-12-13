package main.java.sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import main.java.Gateways.UserLoginGateway;
import main.java.UseCases.UserManager;

import java.net.URL;
import java.util.ResourceBundle;

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

    /**
     * When Create Account button is pressed, the method checks the input information and allows or rejects the
     * organizer to create an account, depending on the information of the existing accounts.
     * @param event is the action of the button, that is associated with the method, is clicked.
     */
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

    /**
     * A setter to change the value of the attribute userManager.
     * @param userManager is an instance of UserManager class.
     */
    public void setUserManager(UserManager userManager){
        this.userManager = userManager;
    }

    /**
     * A setter to change the value of the attribute userLoginGateway
     * @param userLoginGateway is an instance of UserLoginGateway class
     */
    public void setUserLoginGateway(UserLoginGateway userLoginGateway){
        this.userLoginGateway = userLoginGateway;
    }

    /**
     * Shows the list of roles that the organizer can create.
     */
    public void showOptions(){
        roleMenu.getItems().addAll("organizer", "attendee", "speaker", "admin");
    }

    /**
     * The method is called as soon a the program is run to prepare the controller for the scene before the scene is
     * reached.
     */
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
