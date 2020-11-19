package Controllers;

import Entities.Event;
import Gateways.EventGateway;
import Gateways.EventStatusGateway;
import UseCases.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class EventStatusChanger {

    private EventStatusGateway eventStatusGateway;

    public EventStatusChanger() {
        this.eventStatusGateway = new EventStatusGateway();
    }

    public boolean signUpForEvent(String username, String eventTitle) throws IOException {
        String db = "phase1/src/DB/EventStatusData.ser";
        eventStatusGateway.setEventStatus(eventStatusGateway.loadFromFile(db));
        boolean returnVal = eventStatusGateway.getEventStatus().signUpEvent(username,eventTitle);
        eventStatusGateway.saveToFile(db);
        return returnVal;
    }

    public boolean cancelEvent(String username, String eventTitle) throws IOException {
        String db = "phase1/src/DB/EventStatusData.ser";
        eventStatusGateway.setEventStatus(eventStatusGateway.loadFromFile(db));
        boolean returnVal = eventStatusGateway.getEventStatus().cancelEventEnrolment(username,eventTitle);
        eventStatusGateway.saveToFile(db);
        return returnVal;
    }

//    private EventGateway eventGateway = new EventGateway();
//    private EventStatus eventStatus;
//
//    public EventStatusChanger(EventStatus eventStatus) {
//        this.eventStatus = eventStatus;
//    }
//
//    // joinEvent takes a String (eventTitle) as the input.
//    // This is how it works:
//    // The presenter asks user to input the event title that user want to sign up for
//    // --> presenter calls EventStatusChanger.joinEvent(eventTitle)
//    // --> save signed up event into EventStatus class
//    // problem: where do we instantiate EventStatusChanger and EventStatus in presenter?
//    public boolean joinEvent(String eventTitle) throws ClassNotFoundException {
//        try {
//            HashMap<LocalDate, ArrayList<Event>> existingEvents = this.eventGateway.getEvents();
//            for (ArrayList<Event> value : existingEvents.values()) {
//                for (Event searchEvent : value) {
//                    if (searchEvent.getTitle().equals(eventTitle)) {
//                        this.eventStatus.signUp(searchEvent);
//                        return true;
//                    }
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            ex.printStackTrace();
//        } return false;
//    }
}
