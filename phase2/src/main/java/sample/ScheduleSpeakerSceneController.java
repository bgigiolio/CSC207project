package main.java.sample;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import main.java.Gateways.BuildingGateway;
import main.java.Gateways.EventGateway;
import main.java.Gateways.UserLoginGateway;
import main.java.UseCases.BuildingManager;
import main.java.UseCases.EventManager;
import main.java.UseCases.UserManager;

public class ScheduleSpeakerSceneController {

    private UUID id;

    private String username;

    private UserManager userManager;

    private UserLoginGateway userLoginGateway;

    private BuildingManager buildingManager;

    private BuildingGateway buildingGateway;

    private EventManager eventManager;

    private EventGateway eventGateway;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField speakerNameField;

    @FXML
    private TextField eventUUID;

    @FXML
    private Text errorText;

    @FXML
    private Button actionButton;

    @FXML
    void actionButtonPressed(ActionEvent event) {
        String idString;
        if (eventUUID.getText() == null || speakerNameField.getText() == null){
            errorText.setText("Please fill in all of the fields.");
            return;
        }
        this.username = speakerNameField.getText();
        if (!userManager.checkUsername(this.username)){
            errorText.setText("Not a valid speaker");
            return;
        }
        if (!userManager.getUserRole(speakerNameField.getText()).equalsIgnoreCase("speaker")){
            errorText.setText("This user is not a speaker");
            return;
        }
        idString = eventUUID.getText();
        try{
            this.id = UUID.fromString(idString);
        }catch (IllegalArgumentException e){
            errorText.setText("Invalid UUID format");
            return;
        }
        if (eventManager.setSpeaker(id, this.username) & userManager.addTalk(this.username, id)){
            errorText.setText("Speaker Successfully added!");
        }
        this.buildingGateway.save(buildingManager);
        this.eventGateway.save(eventManager);
        this.userLoginGateway.save(userManager);
    }
    public void setEventManager(EventManager eventManager){
        this.eventManager = eventManager;
    }
    public void setEventGateway(EventGateway eventGateway){
        this.eventGateway = eventGateway;
    }
    public void setBuilding(BuildingManager building){
        this.buildingManager = building;
    }

    public void setBuildingGateway(BuildingGateway buildingGateway){
        this.buildingGateway = buildingGateway;
    }

    public void setUserManager(UserManager userManager){
        this.userManager = userManager;
    }
    public void setUserLoginGateway(UserLoginGateway userLoginGateway){
        this.userLoginGateway = userLoginGateway;
    }

    @FXML
    void initialize() {
        assert speakerNameField != null : "fx:id=\"speakerNameField\" was not injected: check your FXML file 'scheduleSpeakerScene.fxml'.";
        assert eventUUID != null : "fx:id=\"eventUUID\" was not injected: check your FXML file 'scheduleSpeakerScene.fxml'.";
        assert errorText != null : "fx:id=\"errorText\" was not injected: check your FXML file 'scheduleSpeakerScene.fxml'.";
        assert actionButton != null : "fx:id=\"actionButton\" was not injected: check your FXML file 'scheduleSpeakerScene.fxml'.";

    }
}
