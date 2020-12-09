package main.java.sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;


public class OpeningController extends AbstractController{
    @FXML
    Button newUserButton;
    @FXML
    Button retUserButton;
    @FXML
    Button loginButton;
    @FXML
    Button backButton;
    Stage primaryStage;
    Scene currScene;

    String string;
    //reminder: initializer


    public void handleNewUserButton(ActionEvent event) throws IOException, NullPointerException {
        helperButtonHandler(event, "NewUserLoginScene.fxml");
        System.out.println(this.string);
    }

    public void handleRetUserButton(ActionEvent event) throws IOException {
        helperButtonHandler(event, "RetUserLoginScene.fxml");
    }

    public void setString(String string){
        this.string = string;
    }

}
