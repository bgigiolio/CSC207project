package main.java.UseCases;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import main.java.Entities.Event;
import java.util.ArrayList;

/**
 * <h1>EventTableView</h1>
 * Manage events with FilteredList.
 *
 * @author Morgan Chang
 * @version Phase2
 */
public class EventTableView {

    /**
     * The username of current user.
     */
    private final String username;

    /**
     * Stores all events as an ObservableList.
     */
    private final ObservableList<Event> allEvents;

    /**
     * Stores all events as a FilteredList to allow for filtering.
     */
    private final FilteredList<Event> filteredListEvents;

    /**
     * Stores scheduled events for user of username as an ObservableList.
     */
    private final ObservableList<Event> eventsRegistered;

    /**
     * Stores scheduled events for user of username as an FilteredList to allow for filtering.
     */
    private final FilteredList<Event> filteredListEventsRegistered;

    /**
     * Constructs an EventTableView.
     * @param eventManager the EventManager of the system
     * @param username of the current user
     */
    public EventTableView(EventManager eventManager, String username) {
        this.username = username;
        ArrayList<Event> allEvents = eventManager.getEvents();
        ObservableList<Event> eventsData = FXCollections.observableArrayList();
        eventsData.addAll(allEvents);
        this.allEvents = eventsData;
        this.filteredListEvents = new FilteredList<>(this.allEvents, p -> true);
        this.eventsRegistered = getEventsOfUsername();
        this.filteredListEventsRegistered = new FilteredList<>(this.eventsRegistered, p -> true);
    }

    /**
     * Get filteredListEventsRegistered.
     */
    public FilteredList<Event> getFilteredListEventsRegistered() {
        return this.filteredListEventsRegistered;
    }

    /**
     * Constructs eventsRegistered.
     */
    public ObservableList<Event> getEventsOfUsername() {
        ObservableList<Event> toReturn = FXCollections.observableArrayList();
        for (Event event : allEvents) {
            if (event.getAttendees().contains(this.username)) {
                eventsRegistered.add(event);
            }
            return toReturn;
        } return FXCollections.observableArrayList();
    }

    /**
     * Get allEvents.
     */
    public ObservableList<Event> getAllEvents() {
        return this.allEvents;
    }

    /**
     * Get filteredListEvents.
     */
    public FilteredList<Event> getFilteredListEvents () {
        return this.filteredListEvents;
    }

    /**
     * Set titleColumn with title of all events in the system.
     * @param titleColumn of a TableView
     */
    public void setTitleColumn(TableColumn<Object, String> titleColumn) {
        titleColumn.setCellValueFactory(
                new PropertyValueFactory<>("title"));
    }

    /**
     * Set locationColumn with location of all events in the system.
     * @param locationColumn of a TableView
     */
    public void setLocationColumn(TableColumn<Object, String> locationColumn) {
        locationColumn.setCellValueFactory(
                new PropertyValueFactory<>("location"));
    }

    /**
     * Set datetimeColumn with datetime of all events in the system.
     * @param datetimeColumn of a TableView
     */
    public void setDatetimeColumn(TableColumn<Object, String> datetimeColumn) {
        datetimeColumn.setCellValueFactory(
                new PropertyValueFactory<>("datetime"));
    }

    /**
     * Set speakerColumn with speaker of all events in the system.
     * @param speakerColumn of a TableView
     */
    public void setSpeakerColumn(TableColumn<Object, String> speakerColumn) {
        speakerColumn.setCellValueFactory(
                new PropertyValueFactory<>("speaker"));
    }

    /**
     * Set durationColumn with duration of all events in the system.
     * @param durationColumn of a TableView
     */
    public void setDurationColumn(TableColumn<Object, String> durationColumn) {
        durationColumn.setCellValueFactory(
                new PropertyValueFactory<>("duration"));
    }

    /**
     * Set capacityColumn with capacity of all events in the system.
     * @param capacityColumn of a TableView
     */
    public void setCapacityColumn(TableColumn<Object, String> capacityColumn) {
        capacityColumn.setCellValueFactory(
                new PropertyValueFactory<>("capacity"));
    }

    /**
     * Set idColumn with id of all events in the system.
     * @param idColumn of a TableView
     */
    public void setIdColumn(TableColumn<Object, String> idColumn) {
        idColumn.setCellValueFactory(
                new PropertyValueFactory<>("uuid"));
    }

    /**
     * Set typeColumn with type of all events in the system.
     * @param typeColumn of a TableView
     */
    public void setTypeColumn(TableColumn<Object, String> typeColumn) {
        typeColumn.setCellValueFactory(
                new PropertyValueFactory<>("type"));
    }
}
