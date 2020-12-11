package main.java.UseCases;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.java.Entities.Event;
import main.java.Gateways.EventGateway;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class EventTableView {

    BuildingManager buildingManager;
    ObservableList<Event> allEvents;
    TableView<Event> eventTableView;

    public EventTableView(BuildingManager buildingManager) {
        this.buildingManager = buildingManager;
        ArrayList<Event> allEvents = new ArrayList<>();
        for (String room : buildingManager.getBuilding().keySet()) {
            Schedule2 schedule = buildingManager.getBuilding().get(room);
            allEvents.addAll(schedule.getAllEvents());
        }
        ObservableList<Event> eventsData = FXCollections.observableArrayList();
        eventsData.addAll(allEvents);
        this.allEvents = eventsData;
        this.eventTableView = new TableView<>();

    }

    public ObservableList<Event> getAllEvents() {
        return this.allEvents;
    }

    public ObservableList<Object> getFilteredListEvents () {
        return new FilteredList(this.allEvents, p -> true);
    }

    public TableView<Event> getTableViewEvents () {
        return this.eventTableView;
    }

    public void setTitleColumn(TableColumn<Object, String> titleColumn) {
        titleColumn.setCellValueFactory(
                new PropertyValueFactory<Object, String>("title"));
    }

    public void setLocationColumn(TableColumn<Object, String> locationColumn) {
        locationColumn.setCellValueFactory(
                new PropertyValueFactory<Object, String>("location"));
    }

    public void setDatetimeColumn(TableColumn<Object, String> datetimeColumn) {
        datetimeColumn.setCellValueFactory(
                new PropertyValueFactory<Object, String>("datetime"));
    }

    public void setSpeakerColumn(TableColumn<Object, String> speakerColumn) {
        speakerColumn.setCellValueFactory(
                new PropertyValueFactory<Object, String>("speaker"));
    }

    public void setDurationColumn(TableColumn<Object, String> durationColumn) {
        durationColumn.setCellValueFactory(
                new PropertyValueFactory<Object, String>("duration"));
    }

    public void setCapacityColumn(TableColumn<Object, String> capacityColumn) {
        capacityColumn.setCellValueFactory(
                new PropertyValueFactory<Object, String>("capacity"));
    }


}
