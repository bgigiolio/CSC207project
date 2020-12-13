package main.java.sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import main.java.Gateways.BuildingGateway;
import main.java.Gateways.EventGateway;
import main.java.UseCases.BuildingManager;
import main.java.UseCases.EventManager;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

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


    /**
     * When the button is pressed, the method removes the specified event, if it exists.
     * @param event is the action of the button, that is associated with the method, is clicked.
     */
    @FXML
    void actionButtonPressed(ActionEvent event) {
        try{
            this.id = UUID.fromString(UUIDField.getText());
        }catch (IllegalArgumentException e){
            errorText.setText("Improper UUID");
            return;
        }

        if (eventManager.deleteEvent(id) && buildingManager.deleteEvent(id)){
            errorText.setText("Event successfully removed!");
            this.eventGateway.save(this.eventManager);
            this.buildingGateway.save(this.buildingManager);
        }else{
            errorText.setText("Event could not be removed or doesn't exist");
        }

    }
    /**
     * Setter method for the attribute buildingManager.
     * @param buildingManager is an instance of the BuildingManager class.
     */
    public void setBuildingManager(BuildingManager buildingManager){
        this.buildingManager = buildingManager;
    }
    /**
     * Setter method for the attribute buildingGateway.
     * @param buildingGateway is an instance of the BuildingGateway class.
     */
    public void setBuildingGateway(BuildingGateway buildingGateway){
        this.buildingGateway = buildingGateway;
    }
    /**
     * Setter method for the attribute eventManager.
     * @param eventManager is an instance of the EventManager class.
     */
    public void setEventManager(EventManager eventManager){
        this.eventManager = eventManager;
    }
    /**
     * Setter method for the attribute eventGateway.
     * @param eventGateway is an instance of the EventGateway class.
     */
    public void setEventGateway(EventGateway eventGateway){
        this.eventGateway = eventGateway;
    }
    /**
     * The method is called as soon a the program is run to prepare the controller for the scene before the scene is
     * reached.
     */
    @FXML
    void initialize() {
        assert eventRemovedText != null : "fx:id=\"eventRemovedText\" was not injected: check your FXML file 'EventRemoverScene.fxml'.";
        assert UUIDField != null : "fx:id=\"UUIDField\" was not injected: check your FXML file 'EventRemoverScene.fxml'.";
        assert actionButton != null : "fx:id=\"actionButton\" was not injected: check your FXML file 'EventRemoverScene.fxml'.";
        assert errorText != null : "fx:id=\"errorText\" was not injected: check your FXML file 'EventRemoverScene.fxml'.";

    }
}
