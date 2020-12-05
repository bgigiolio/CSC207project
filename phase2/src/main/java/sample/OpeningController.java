package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

import static com.sun.corba.se.impl.util.Utility.printStackTrace;


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

    //reminder: initializer


    public void handleNewUserButton(ActionEvent event) throws IOException, NullPointerException {
        helperButtonHandler(event, "NewUserLoginScene.fxml");
        System.out.println(event.getSource());
    }

    public void handleRetUserButton(ActionEvent event) throws IOException {
        helperButtonHandler(event, "RetUserLoginScene.fxml");
    }


}
