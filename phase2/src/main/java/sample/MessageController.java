package main.java.sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
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
    @FXML Label sentValid;
    @FXML Label sentInvalid;

    main.java.Controllers.MessageController messageController = new main.java.Controllers.MessageController();

    public void setSender(String sender){
        this.sender = sender;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void handleSendPane(MouseEvent event){
        sentValid.setVisible(false);
        sentInvalid.setVisible(false);
    }

    public void handleSendButton(ActionEvent event) {

        messageController.setMessageSystem(this.message.getText(), this.toUsername.getText(),this.sender);
        if (messageController.sendMessage()){
            sentValid.setVisible(true);
        }
        else{
            sentInvalid.setVisible(true);
        }


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
