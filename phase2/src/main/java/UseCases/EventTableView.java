package main.java.UseCases;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.java.Entities.Event;

import java.util.ArrayList;

public class EventTableView {

    private final ObservableList<Event> allEvents;
    private final TableView<Event> eventTableView;
    private final FilteredList<Event> filteredListEvents;

    public EventTableView(EventManager eventManager) {
        ArrayList<Event> allEvents = eventManager.getEvents();

        ObservableList<Event> eventsData = FXCollections.observableArrayList();
        eventsData.addAll(allEvents);
        this.allEvents = eventsData;
        this.eventTableView = new TableView<>();
        this.filteredListEvents = new FilteredList(this.allEvents, p -> true);
    }

    public ObservableList<Event> getAllEvents() {
        return this.allEvents;
    }

    public FilteredList<Event> getFilteredListEvents () {
        return this.filteredListEvents;
    }

    public TableView<Event> getTableViewEvents () {
        return this.eventTableView;
    }

    public void setTitleColumn(TableColumn<Object, String> titleColumn) {
        titleColumn.setCellValueFactory(
                new PropertyValueFactory<>("title"));
    }

    public void setLocationColumn(TableColumn<Object, String> locationColumn) {
        locationColumn.setCellValueFactory(
                new PropertyValueFactory<>("location"));
    }

    public void setDatetimeColumn(TableColumn<Object, String> datetimeColumn) {
        datetimeColumn.setCellValueFactory(
                new PropertyValueFactory<>("datetime"));
    }

    public void setSpeakerColumn(TableColumn<Object, String> speakerColumn) {
        speakerColumn.setCellValueFactory(
                new PropertyValueFactory<>("speaker"));
    }

    public void setDurationColumn(TableColumn<Object, String> durationColumn) {
        durationColumn.setCellValueFactory(
                new PropertyValueFactory<>("duration"));
    }

    public void setCapacityColumn(TableColumn<Object, String> capacityColumn) {
        capacityColumn.setCellValueFactory(
                new PropertyValueFactory<>("capacity"));
    }



}
