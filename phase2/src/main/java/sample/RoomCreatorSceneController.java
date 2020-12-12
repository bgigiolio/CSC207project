package main.java.sample;

import java.net.URL;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import main.java.Gateways.BuildingGateway;
import main.java.UseCases.BuildingManager;

public class RoomCreatorSceneController {

    private BuildingManager buildingManager;

    private BuildingGateway buildingGateway;

    private int startHour;

    private int endHour;

    private int startMinute;

    private int endMinute;

    //private LocalDateTime date;

    //private LocalDateTime endDate;

    private String roomTitle;

    private int capacity;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField startTimePrompt;

    @FXML
    private TextField durationPrompt;

    @FXML
    private TextField eventTitlePrompt;

    @FXML
    private TextField capacityPrompt;

    @FXML
    private Text errorText;

    @FXML
    private Button createButton;

    @FXML
    private TextField startingMinutePrompt;

    @FXML
    private TextField endingMinutePrompt;

    @FXML
    void createButtonPressed(ActionEvent event) {
        //LocalDate rawDate = this.datePicker.getValue();
        int startHour = 0;
        int capacity = 0;
        if (startTimePrompt.getText() == null || startingMinutePrompt.getText() == null || durationPrompt.getText() == null
        || endingMinutePrompt.getText() == null || capacityPrompt.getText() == null || eventTitlePrompt.getText() == null){
            errorText.setText("Please fill in every field!");
            return;
        }
        try{
            startHour = Integer.parseInt(startTimePrompt.getText());
        }catch (NumberFormatException e){
            errorText.setText("Please input a valid start hour");
            return;
        }
        try{
            this.startMinute = Integer.parseInt(startingMinutePrompt.getText());
        }catch (NumberFormatException e){
            errorText.setText("Please input a valid starting minute");
            return;
        }
        //this.date = rawDate.atTime(startHour, 0);
        this.startHour = startHour;
        try{
            this.endHour = Integer.parseInt(durationPrompt.getText());
        }catch (NumberFormatException e){
            errorText.setText("Please input a valid duration");
            return;
        }
        try{
            this.endMinute = Integer.parseInt(endingMinutePrompt.getText());
        }catch (NumberFormatException e){
            errorText.setText("Please input a valid ending minute");
            return;
        }
        //this.endDate = rawDate.atTime(startHour + duration, 0);
        this.startHour = startHour;
        this.roomTitle = eventTitlePrompt.getText();
        try{
            capacity = Integer.parseInt(capacityPrompt.getText());
        }catch (NumberFormatException e){
            errorText.setText("Please input a valid capacity");
            return;
        }
        this.capacity = capacity;
        try{
            createRoom();
        }catch (DateTimeException e){
            errorText.setText("Invalid date given, please try again");
        }


    }
    private void createRoom(){
        if (buildingManager.addRoom(roomTitle, LocalTime.of(startHour, startMinute),
                LocalTime.of(endHour, endMinute), this.capacity)){
            errorText.setText("Room Created!");
            this.buildingGateway.save(this.buildingManager);
        }else {
            errorText.setText("Room Creation Failed!");
        }
    }
    public void setBuildingManager(BuildingManager buildingManager){
        this.buildingManager = buildingManager;
    }
    public void setBuildingGateway(BuildingGateway buildingGateway){
        this.buildingGateway = buildingGateway;
    }

    @FXML
    void initialize() {
        //assert datePicker != null : "fx:id=\"datePicker\" was not injected: check your FXML file 'RoomCreatorScene.fxml'.";
        assert startTimePrompt != null : "fx:id=\"startTimePrompt\" was not injected: check your FXML file 'RoomCreatorScene.fxml'.";
        assert durationPrompt != null : "fx:id=\"durationPrompt\" was not injected: check your FXML file 'RoomCreatorScene.fxml'.";
        assert eventTitlePrompt != null : "fx:id=\"eventTitlePrompt\" was not injected: check your FXML file 'RoomCreatorScene.fxml'.";
        assert capacityPrompt != null : "fx:id=\"capacityPrompt\" was not injected: check your FXML file 'RoomCreatorScene.fxml'.";
        assert errorText != null : "fx:id=\"errorText\" was not injected: check your FXML file 'RoomCreatorScene.fxml'.";
        assert createButton != null : "fx:id=\"createButton\" was not injected: check your FXML file 'RoomCreatorScene.fxml'.";

    }
}
