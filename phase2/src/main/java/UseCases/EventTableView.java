package main.java.UseCases;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.java.Entities.Attendee;
import main.java.Entities.Event;
import main.java.Gateways.UserLoginGateway;

import java.util.ArrayList;
import java.util.UUID;

public class EventTableView {

    private final ObservableList<Event> allEvents;
    private final TableView<Event> eventTableView;
    private final FilteredList<Event> filteredListEvents;
    private final ObservableList<Event> eventsRegistered;
    private final FilteredList<Event> filteredListEventsRegistered;


    public EventTableView(EventManager eventManager, String username) {
        ArrayList<Event> allEvents = eventManager.getEvents();

        ObservableList<Event> eventsData = FXCollections.observableArrayList();
        eventsData.addAll(allEvents);
        this.allEvents = eventsData;
        this.eventTableView = new TableView<>();
        this.filteredListEvents = new FilteredList<>(this.allEvents, p -> true);

        this.eventsRegistered = getEventsOfUsername(username);
        this.filteredListEventsRegistered = new FilteredList<>(this.eventsRegistered, p -> true);
    }

    public FilteredList<Event> getFilteredListEventsRegistered() {
        return this.filteredListEventsRegistered;
    }
    public ObservableList<Event> getEventsOfUsername(String username) {
        ObservableList<Event> toReturn = FXCollections.observableArrayList();
        for (Event event : allEvents) {
            if (event.getAttendees().contains(username)) {
                toReturn.add(event);
            }
            return toReturn;
        } return FXCollections.observableArrayList();
    }
//        Attendee user = new UserLoginGateway().read().getAttendeeOfUsername(username);
//        if (!(user == null)) {
//            ArrayList<UUID> uuids = user.getEventsRegistered();
//            ArrayList<Event> events = new ArrayList<>();
//            for (UUID uuid : uuids) {
//                events.add(eventManager.getEvent(uuid));
//            }
//            ObservableList<Event> eventsRegistered = FXCollections.observableArrayList();
//            eventsRegistered.addAll(events);
//        }
//        return eventsRegistered;
//    }

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

    public void setIdColumn(TableColumn<Object, String> idColumn) {
        idColumn.setCellValueFactory(
                new PropertyValueFactory<>("uuid"));
    }

    public void setTypeColumn(TableColumn<Object, String> typeColumn) {
        typeColumn.setCellValueFactory(
                new PropertyValueFactory<>("type"));
    }

    public ObservableList<Event> getEventsRegistered() {
        return this.eventsRegistered;
    }

    public void filterById(FilteredList<Event> toBeFiltered, String text) {
        toBeFiltered.setPredicate(p -> p.getUuid().toString().contains(text.toLowerCase().trim()));
    }


}
