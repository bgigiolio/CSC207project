package main.java.sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class OpeningController implements Initializable {
    public ToggleGroup userType;
    public Button loginButton;


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
    public TextField password;
    @FXML
    private Label invalidLoginText;

    /**
     * The method is called as soon as the program is run to load the necessary information in the controller before the
     * scene that is associated with the controller is loaded.
     * @param location is the location of the root of the scene.
     * @param resources is the resource that is used to locate the root of the scene.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        userRole.getItems().addAll("organizer", "attendee", "speaker", "admin");

    }

    /**
     * Handles the action of New User option being selected. Clicking this button sets the User Role choice box visible
     * and lets the user to select the user role that they would like to create account of.
     * @param event the action of the button being clicked
     */
    public void handleNewUser(ActionEvent event) {
        this.userRole.setVisible(true);
        this.userRoleLabel.setVisible(true);
    }

    /**
     * Handles the action of Returning User option being selected. Clicking this button sets the User Role choice box
     * invisible since they would need to enter the information of an existing account.
     * @param event the action of the button being clicked
     */
    public void handleRetUser(ActionEvent event) {
        this.userRole.setVisible(false);
        this.userRoleLabel.setVisible(false);
    }
    /**
     * When the button is clicked, it will check if the username and password matches. Login will be successful
     * if information matches, unsuccessful otherwise.
     * @param event is the action of clicking the "Login" button
     * @throws IOException to handle a possible exception in the input or output.
     */
    public void handleLoginButton(ActionEvent event) throws IOException {
        Stage primaryStage;

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("HomeScreen.fxml"));
        Parent root = loader.load();
        HomeScreenController hSC = loader.getController();
        primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene homeScene = new Scene(root,600,500);


        String login_attempt = hSC.login(this.username.getText(),this.password.getText());

        if (userType.getSelectedToggle() == newUser && userRole.getValue() != null){
            if (hSC.register(userRole.getValue(), this.username.getText(), this.password.getText()).equalsIgnoreCase("invalid")){
                this.invalidLoginText.setVisible(true);
            }
            else{
                primaryStage.setScene(homeScene);
                primaryStage.show();
            }
        }

        else if (login_attempt.equalsIgnoreCase("invalid")){
            this.invalidLoginText.setVisible(true);
        }
        else{

            primaryStage.setScene(homeScene);
            primaryStage.show();
        }
    }
}
