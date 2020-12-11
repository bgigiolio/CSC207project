package main.java.sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class MessageController implements Initializable {

    @FXML
    ToggleGroup action;
    @FXML
    RadioButton sendMessage;
    @FXML
    RadioButton reviewMessage;
    @FXML
    AnchorPane sendPane;
    @FXML
    AnchorPane reviewPane;
    @FXML
    Button sendButton;
    @FXML
    TextField toUsername;
    @FXML
    TextArea textArea;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void handleSendButton(ActionEvent event) {
//        this.toUsername.getText();
//        this.textArea.getText();


    }


    public void handleMessageAction(javafx.event.ActionEvent event) {
        if (action.getSelectedToggle() == sendMessage){
            sendPane.setVisible(true);
            reviewPane.setVisible(false);
        }
        else{ //action.getSelectedToggle() == reviewMessage
            sendPane.setVisible(false);
            reviewPane.setVisible(true);
        }
    }
}
