package main.java.sample;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import main.java.Gateways.BuildingGateway;
import main.java.Gateways.EventGateway;
import main.java.UseCases.BuildingManager;
import main.java.UseCases.EventManager;

public class EventCreatorSceneController {



    private BuildingManager buildingManager;

    private BuildingGateway buildingGateway;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String roomTitle;

    private String eventTitle;

    private String type;

    private int duration;

    private int eventCapacity;

    private EventManager eventManager;

    private EventGateway eventGateway;

    @FXML
    private ChoiceBox<String> eventTypeChoiceBox;

    @FXML
    private TextField capacityPrompt;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField eventTitlePrompt;

    @FXML
    private TextField roomNamePrompt;

    @FXML
    private DatePicker eventDatePrompt;

    @FXML
    private TextField startHourField;

    @FXML
    private TextField startMinuteField;

    @FXML
    private TextField endHourField;

//    @FXML
//    private TextField endMinuteField;

    @FXML
    private Button createEventButton;

    @FXML
    private Text errorText;

    @FXML
    void createEventButtonPressed(ActionEvent event) {
        //LocalDate rawDate = this.datePicker.getValue();
        int startHour;
        int durHour;
        int startMinute;
        int durMinute;
        LocalDate rawStartDate;
        if (startHourField.getText() == null || startMinuteField.getText() == null || endHourField.getText() == null
                || roomNamePrompt.getText() == null || capacityPrompt.getText() == null ||
                eventTitlePrompt.getText() == null || roomNamePrompt.getText() == null ||
                eventDatePrompt.getValue() == null || eventTypeChoiceBox.getValue() == null){
            errorText.setText("Please fill in every field!");
            return;
        }
        try{
            startHour = Integer.parseInt(startHourField.getText());
        }catch (NumberFormatException e){
            errorText.setText("Please input a valid start hour");
            return;
        }
        try{
            this.eventCapacity = Integer.parseInt(startHourField.getText());
        }catch (NumberFormatException e){
            errorText.setText("Please input a capacity");
            return;
        }
        try{
            startMinute = Integer.parseInt(startMinuteField.getText());
        }catch (NumberFormatException e){
            errorText.setText("Please input a valid starting minute");
            return;
        }
        rawStartDate = eventDatePrompt.getValue();

        this.startDate = rawStartDate.atTime(startHour, startMinute);

        try{
            durHour = Integer.parseInt(endHourField.getText());
        }catch (NumberFormatException e){
            errorText.setText("Please input a valid ending hour");
            return;
        }
//        try{
//            durMinute = Integer.parseInt(endMinuteField.getText());
//        }catch (NumberFormatException e){
//            errorText.setText("Please input a valid ending minute");
//            return;
//        }

        //this.endDate = rawDate.atTime(startHour + duration, 0);
        this.roomTitle = roomNamePrompt.getText();
        this.type = eventTypeChoiceBox.getValue();
        this.eventTitle = eventTitlePrompt.getText();
        this.duration = durHour;
        createEvent();
    }
    private void createEvent(){
        if(this.type.equalsIgnoreCase("No speaker")){
            if(!eventManager.addEvent(this.eventTitle, this.roomTitle, this.startDate, this.duration, eventCapacity,
                    "event", this.buildingManager)){
                errorText.setText("Event could not be created!");
                return;
            }

        }else if(this.type.equalsIgnoreCase("Talk")){
            if(!eventManager.addEvent(this.eventTitle, this.roomTitle, this.startDate, this.duration, eventCapacity,
                    "talk", this.buildingManager)) {
                errorText.setText("Event could not be created!");
                return;
            }

        }else if(this.type.equalsIgnoreCase("Panel")){
            if(!eventManager.addEvent(this.eventTitle, this.roomTitle, this.startDate, this.duration, eventCapacity,
                    "panelDiscussion", this.buildingManager)) {
                errorText.setText("Event could not be created!");
                return;
            }
        }
        errorText.setText("Event Successfully Created");
        this.eventGateway.save(this.eventManager);
        this.buildingGateway.save(this.buildingManager);
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
    public void showOptions(){
        eventTypeChoiceBox.getItems().addAll("No speaker", "Talk", "Panel discussion");
    }

    @FXML
    void initialize() {
        assert eventTitlePrompt != null : "fx:id=\"eventTitlePrompt\" was not injected: check your FXML file 'EventCreatorScene.fxml'.";
        assert roomNamePrompt != null : "fx:id=\"roomNamePrompt\" was not injected: check your FXML file 'EventCreatorScene.fxml'.";
        assert eventDatePrompt != null : "fx:id=\"eventDatePrompt\" was not injected: check your FXML file 'EventCreatorScene.fxml'.";
        assert startHourField != null : "fx:id=\"startHourField\" was not injected: check your FXML file 'EventCreatorScene.fxml'.";
        assert startMinuteField != null : "fx:id=\"startMinuteField\" was not injected: check your FXML file 'EventCreatorScene.fxml'.";
        assert endHourField != null : "fx:id=\"endHourField\" was not injected: check your FXML file 'EventCreatorScene.fxml'.";
        //assert endMinuteField != null : "fx:id=\"endMinuteField\" was not injected: check your FXML file 'EventCreatorScene.fxml'.";
        assert createEventButton != null : "fx:id=\"createEventButton\" was not injected: check your FXML file 'EventCreatorScene.fxml'.";
        assert errorText != null : "fx:id=\"errorText\" was not injected: check your FXML file 'EventCreatorScene.fxml'.";
        Platform.runLater(this::showOptions);
    }
}
