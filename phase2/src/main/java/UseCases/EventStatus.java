package main.java.UseCases;

import main.java.Entities.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * <h1>Event Status</h1>
 * Represents the entire system of users and their registrations for events.
 *
 * @author Morgan Chang
 */
public class EventStatus implements Serializable {

    /**
     * The hashmap that maps Attendee.username to a List of Event.eventTitle that user
     * had signed up for.
     * Initialized with an empty HashMap.
     */
    private final HashMap<String, List<String>> usernameToEvents = new HashMap<>();

    /**
     * Register the user of username for the event of eventTitle,
     * iff the user has not signed up for the event yet.
     *
     * @return true iff user of username has successfully signed up for event of eventTitle.
     */
    public int signUpEvent(String username, String eventTitle, BuildingManager building) {
        List<String> eventsRegistered = usernameToEvents.get(username);
        if (!(eventsRegistered == null) && !eventsRegistered.contains(eventTitle)) {
            if (building.getEvent(eventTitle).getAttendees().size() >= building.getEvent(eventTitle).getEventCapacity()) {
                return 2;
            }
            eventsRegistered.add(eventTitle);
            usernameToEvents.put(username, eventsRegistered);
            return 0;
        } else { return 1; }
    }

    /**
     * Cancel the enrollment of the user of <code>username</code> in the event of
     * <code>eventTitle</code> if the user had registered for the event.
     *
     * @return true iff the user has successfully cancelled his enrollment in event of eventTitle.
     */
    public boolean cancelEventEnrolment(String username, String eventTitle) {
        List<String> eventsRegistered = usernameToEvents.get(username);
        if (eventsRegistered != null && eventsRegistered.contains(eventTitle)) {
            eventsRegistered.remove(eventTitle);
            usernameToEvents.put(username, eventsRegistered);
            return true;
        }
        return false;
    }

}
