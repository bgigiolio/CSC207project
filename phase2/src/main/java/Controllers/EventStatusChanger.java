package main.java.Controllers;

import main.java.Gateways.*;
import main.java.UseCases.BuildingManager;
import main.java.UseCases.EventStatus;

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
    private final EventStatusGateway eventStatusGateway = new EventStatusGateway();

    /**
     * Read from a file, EventStatusData.ser, about existing users' event registrations,
     * register the user of username for the event of eventTitle
     * iff the user has not signed up for the event yet,
     * and update the file.
     *
     * @return true iff username has successfully signed up for event of eventTitle.
     */
    public int signUpChanger(String username, String eventTitle, BuildingManager building){
        EventStatus obj = eventStatusGateway.read();
        int returnVal = obj.signUpEvent(username,eventTitle, building);
        eventStatusGateway.save(obj);
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
    public boolean cancelChanger(String username, String eventTitle) {
        EventStatus obj = eventStatusGateway.read();
        boolean returnVal = obj.cancelEventEnrolment(username,eventTitle);
        eventStatusGateway.save(obj);
        return returnVal;
    }

}
