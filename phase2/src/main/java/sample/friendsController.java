package main.java.sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import main.java.UseCases.*;


public class friendsController {
    private UserManager userManager;
    private String username;

    @FXML
    private RadioButton addFriendButton;

    @FXML
    private ToggleGroup actionChoice;

    @FXML
    private RadioButton removeFriendButton;

    @FXML
    private Button actionButton;

    @FXML
    private Text errorMessageText;

    @FXML
    private TextField friendUsername;

    private boolean add;

    @FXML
    void addFriendSelected(ActionEvent event) {
        actionButton.setText("Add friend");
        add = true;
    }

    @FXML
    void removeFriendSelected(ActionEvent event) {
        actionButton.setText("Remove friend");
        add = false;
    }

    public void setUserManager(UserManager userManager){
        this.userManager = userManager;
    }

    public void setUsername(String username){
        this.username = username;
    }

    @FXML
    void actionButtonPressed(ActionEvent event) {
        String fusername = friendUsername.getText();
        if (add){
            if (userManager.addFriend(this.username, fusername)){ //& eventManager.addAttendee(id, username)){
                String num = Integer.toString(this.userManager.getNumOfFriends(this.username));
                errorMessageText.setText(fusername + " added to your friends. You now have " + num + " friend(s)." );
                errorMessageText.setStyle("-fx-text-fill: green; -fx-font-size: 16px;");
            }
            else{
                errorMessageText.setText("User with username " + fusername + " does not exist.");
                errorMessageText.setStyle("-fx-text-fill: red; -fx-font-size: 16px;");
            }
        }
        else{
            if (userManager.removeFriend(this.username, fusername)){
                String num = Integer.toString(this.userManager.getNumOfFriends(this.username));
                errorMessageText.setText(fusername + " removed from your friends. You now have " + num + " friend(s)." );
                errorMessageText.setStyle("-fx-text-fill: green; -fx-font-size: 16px;");
            }
            else{
                errorMessageText.setText("User with username " + fusername + " does not exist in your friends.");
                errorMessageText.setStyle("-fx-text-fill: red; -fx-font-size: 16px;");

            }
        }


    }

    @FXML
    void initialize(){
        assert friendUsername != null : "fx:id=\"friendUsername\" was not injected: check your FXML file 'Untitled'.";
        assert errorMessageText != null : "fx:id=\"errorMessageText\" was not injected: check your FXML file 'Untitled'.";
        assert addFriendButton != null : "fx:id=\"addFriendButton\" was not injected: check your FXML file 'Untitled'.";
        assert actionChoice != null : "fx:id=\"actionChoice\" was not injected: check your FXML file 'Untitled'.";
        assert removeFriendButton != null : "fx:id=\"removeFriendButton\" was not injected: check your FXML file 'Untitled'.";
        assert actionButton != null : "fx:id=\"actionButton\" was not injected: check your FXML file 'Untitled'.";
    }

}