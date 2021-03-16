package Controllers;

import Gateways.*;
import java.io.IOException;

/**
 * <h1>Event Status Changer</h1>
 * Controls the gateway responsible for storing EventStatus object.
 *
 * @author Morgan Chang
 * @version phase1
 */
public class EventStatusChanger {

    /**
     * The gateway to be controlled.
     */
    private EventStatusGateway eventStatusGateway = new EventStatusGateway();

    /**
     * Read from a file, EventStatusData.ser, about existing users' event registrations,
     * register the user of username for the event of eventTitle
     * iff the user has not signed up for the event yet,
     * and update the file.
     *
     * @return true iff username has successfully signed up for event of eventTitle.
     */
    public boolean signUpChanger(String username, String eventTitle) throws IOException {
        String db = "phase1/src/DB/EventStatusData.ser";
        eventStatusGateway.setEventStatus(eventStatusGateway.loadFromFile(db));
        boolean returnVal = eventStatusGateway.getEventStatus().signUpEvent(username,eventTitle);
        eventStatusGateway.saveToFile(db);
        return returnVal;
    }

    /**
     * Read from a file, EventStatusData.ser, about existing users' event registrations,
     * cancel the enrollment of the user of username from the event of eventTitle
     * iff the user had signed up for the event,
     * and update the file.
     *
     * @return true iff the user has successfully cancel his enrollment in event of eventTitle.
     */
    public boolean cancelChanger(String username, String eventTitle) throws IOException {
        String db = "phase1/src/DB/EventStatusData.ser";
        eventStatusGateway.setEventStatus(eventStatusGateway.loadFromFile(db));
        boolean returnVal = eventStatusGateway.getEventStatus().cancelEventEnrolment(username,eventTitle);
        eventStatusGateway.saveToFile(db);
        return returnVal;
    }

}
