package Controllers;

import Entities.Event;
import Gateways.EventGateway;
import UseCases.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class EventStatusChanger {

    private EventGateway eventGateway = new EventGateway();
    private EventStatus eventStatus;

    public EventStatusChanger(EventStatus eventStatus) {
        this.eventStatus = eventStatus;
    }

    public boolean joinEvent(String eventTitle) throws ClassNotFoundException {
        try {
            HashMap<LocalDate, ArrayList<Event>> existingEvents = this.eventGateway.getEvents();
            for (ArrayList<Event> value : existingEvents.values()) {
                for (Event searchEvent : value) {
                    if (searchEvent.getTitle().equals(eventTitle)) {
                        this.eventStatus.signUp(searchEvent);
                        return true;
                    }
                }
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } return false;

    }
}
