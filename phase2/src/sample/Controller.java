package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;


public class Controller {
    @FXML
    Button newUserButton;
    @FXML
    Button retUserButton;
    @FXML
    Button loginButton;
    @FXML
    Button backButton;

    //reminder: initializer
    public void handleNewUserButton(ActionEvent event) throws IOException {
 //or use static method
        Parent newUserRoot = FXMLLoader.load(getClass().getResource("NewUserLoginScene.fxml"));
        Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene newUserLoginScene = new Scene(newUserRoot);
        primaryStage.setScene(newUserLoginScene);
        primaryStage.show();
    }
    public void handleRetUserButton(){

    }
}
