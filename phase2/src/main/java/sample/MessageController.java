package main.java.sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

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
    String sender;
    @FXML
    TextArea message;
    @FXML
    TextArea inbox;

    main.java.Controllers.MessageController messageController = new main.java.Controllers.MessageController();

    public void setSender(String sender){
        this.sender = sender;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void handleSendButton(ActionEvent event) {

        messageController.setSender(this.sender);
        messageController.setReceiver(this.toUsername.getText());
        messageController.setMessageString(this.message.getText());

        messageController.sendMessage();
    }


    public void handleMessageAction(ActionEvent event) {
        if (action.getSelectedToggle() == sendMessage){
            sendPane.setVisible(true);
            reviewPane.setVisible(false);
        }
        else{ //action.getSelectedToggle() == reviewMessage
            sendPane.setVisible(false);
            reviewPane.setVisible(true);

            StringBuilder builder = new StringBuilder();

            for ( String i : messageController.getMessageForMe(this.sender)){
                builder.append(i).append("\n");
            }
            this.inbox.setText(String.valueOf(builder));

        }
    }
}
