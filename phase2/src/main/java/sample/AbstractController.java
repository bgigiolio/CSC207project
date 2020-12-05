package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AbstractController {


    public void helperButtonHandler(ActionEvent event, String fxml) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene userLoginScene = new Scene(root,300,275);
        primaryStage.setScene(userLoginScene);
        primaryStage.show();
    }

    public void handleBackButton(ActionEvent event) throws IOException {
        helperButtonHandler(event, "OpeningScene.fxml");
    }
}
