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
import main.java.UseCases.BuildingManager;
import main.java.UseCases.EventManager;

public class EventRemoverSceneController {

    private UUID id;

    private EventManager eventManager;

    private EventGateway eventGateway;

    private BuildingManager buildingManager;

    private BuildingGateway buildingGateway;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text eventRemovedText;

    @FXML
    private TextField UUIDField;

    @FXML
    private Button actionButton;

    @FXML
    private Text errorText;

    @FXML
    void actionButtonPressed(ActionEvent event) {
        try{
            this.id = UUID.fromString(UUIDField.getText());
        }catch (IllegalArgumentException e){
            errorText.setText("Improper UUID");
            return;
        }

        if (eventManager.deleteEvent(id) & buildingManager.deleteEvent(id)){
            errorText.setText("Event successfully removed!");
        }else{
            errorText.setText("Event could not be removed or doesn't exist");
        }

    }
    public void setBuildingManager(BuildingManager buildingManager){
        this.buildingManager = buildingManager;
    }

    public void setBuildingGateway(BuildingGateway buildingGateway){
        this.buildingGateway = buildingGateway;
    }
    public void setEventManager(EventManager eventManager){
        this.eventManager = eventManager;
    }
    public void setEventGateway(EventGateway eventGateway){
        this.eventGateway = eventGateway;
    }

    @FXML
    void initialize() {
        assert eventRemovedText != null : "fx:id=\"eventRemovedText\" was not injected: check your FXML file 'EventRemoverScene.fxml'.";
        assert UUIDField != null : "fx:id=\"UUIDField\" was not injected: check your FXML file 'EventRemoverScene.fxml'.";
        assert actionButton != null : "fx:id=\"actionButton\" was not injected: check your FXML file 'EventRemoverScene.fxml'.";
        assert errorText != null : "fx:id=\"errorText\" was not injected: check your FXML file 'EventRemoverScene.fxml'.";

    }
}
