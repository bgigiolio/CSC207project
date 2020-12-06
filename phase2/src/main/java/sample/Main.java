package main.java.sample;

import main.java.Gateways.UserLoginInfo;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.*;

public class Main extends Application{

    UserLoginInfo userLoginInfo = new UserLoginInfo(); //instantiates with empty class variables.

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("OpeningScene.fxml"));

        Scene openingScene = new Scene(root, 300, 275);
        primaryStage.setTitle("Building Manager");
        primaryStage.setScene(openingScene);
        primaryStage.show();

    }
    public UserLoginInfo getUserLoginInfo(){
        return this.userLoginInfo;
    }









//    //    Button button;
//    Button newButton;
//    Button returningButton;
//    Button newLoginButton;
//    Button retLoginButton;
//
//
//    Scene sceneIntro;
//    Scene sceneNewLogin;
//    Scene sceneReturningLogin;
//





////
////        button = new Button();
////        button.setText("New user");
////        StackPane layout = new StackPane();
////        layout.getChildren().add(button);
//
//        primaryStage.setTitle("ConfApp");
//        primaryStage.show();
//
//
//        //Opening window
//        newButton = new Button("New User");
//        returningButton = new Button("Returning User");
////      New user button
//        newButton.setOnAction(event -> primaryStage.setScene(sceneNewLogin));
////      Returning user button
//        returningButton.setOnAction(event -> primaryStage.setScene(sceneReturningLogin));
//
//
//
//        VBox vBox = new VBox();
//        vBox.setSpacing(10);
//        ObservableList<Node> buttonList = vBox.getChildren();
//        buttonList.addAll(newButton, returningButton);
//        vBox.setAlignment(Pos.CENTER);
//        sceneIntro = new Scene(vBox, 300, 250);
//        primaryStage.setScene(sceneIntro);
//        primaryStage.show();
//
//
//
//        //New login window
//        VBox newLogVBox = new VBox();
//        sceneNewLogin = new Scene(newLogVBox, 300, 250);
//        primaryStage.show();
//        TextField newUsername = new TextField("New Username");
//        TextField newPassword = new TextField("New Password");
//        newUsername.setMaxWidth(150);
//        newPassword.setMaxWidth(150);
//        ObservableList<Node> inputs = newLogVBox.getChildren();
//        newLoginButton = new Button("Login");
//        inputs.addAll(newUsername, newPassword,newLoginButton);
//        newLogVBox.setAlignment(Pos.CENTER);
//
//
//        //Returning login window
//        retLoginButton = new Button("Login");
//        GridPane retLogGrid = new GridPane();
//        retLogGrid.setAlignment(Pos.CENTER);
//        retLogGrid.setHgap(10);
//        retLogGrid.setVgap(10);
//        Label retUsername = new Label("Username");
//        Label retPassword = new Label("Password");
//        retLogGrid.add(retUsername, 0,1);
//        retLogGrid.add(retPassword, 0,2);
//        TextField usernameBox = new TextField();
//        TextField passwordBox = new TextField();
//        usernameBox.setMaxWidth(150);
//        passwordBox.setMaxWidth(150);
//        retLogGrid.add(usernameBox,1,1);
//        retLogGrid.add(passwordBox,1,2);
//        retLogGrid.add(retLoginButton,1,3);
//        sceneReturningLogin = new Scene(retLogGrid, 300, 250);

//    }


    public static void main(String[] args) {
        launch(args);
    }
}