package sample;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.*;

public class Main extends Application {
    Button button;
    Button newButton;
    Button returningButton;

    @Override
    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
//        primaryStage.setTitle("Login");
//        button = new Button();
//        button.setText("New user");
//        StackPane layout = new StackPane();
//        layout.getChildren().add(button);
//

        newButton = new Button("New User");
        returningButton = new Button("Returning User");
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        ObservableList<Node> buttonList = vBox.getChildren();
        buttonList.addAll(newButton, returningButton);

        vBox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vBox, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
