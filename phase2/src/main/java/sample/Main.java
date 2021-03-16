package main.java.sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.Gateways.UserLoginGateway;
import main.java.UseCases.BuildingManager;
import main.java.UseCases.EventManager;
import main.java.UseCases.UserManager;

import java.io.IOException;


public class Main extends Application {

    FXMLLoader openingLoader;
    FXMLLoader homeLoader;
    UserLoginGateway userLoginGateway;
    BuildingManager buildingManager;
    UserManager userManager;
    EventManager eventManager;

    @Override
    public void start(Stage primaryStage) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("OpeningScene.fxml"));
        openingLoader = new FXMLLoader(Main.class.getResource("OpeningScene.fxml"));
            Parent root = openingLoader.load();
//        OpeningController openingController = loader.getController(); //getting the controller
//        openingController.setString(); //trying to pass an object to another controller
            Scene openingScene = new Scene(root, 300, 275);
            primaryStage.setTitle("Building Manager");
            primaryStage.setScene(openingScene);
            primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }

}




